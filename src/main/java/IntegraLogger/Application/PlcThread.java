package IntegraLogger.Application;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Application.Listeners.TagEmail;
import IntegraLogger.Application.Listeners.TagLogger;
import IntegraLogger.Application.Listeners.TagPersist;
import IntegraLogger.Enviroment.PlcConnection;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import etherip.EtherNetIP;
import etherip.Status.ConnectionFailListener;
import etherip.Tag;
import etherip.TagList;
import etherip.protocol.Connection;
import etherip.scan.Scanner;

import java.util.ArrayList;
import java.util.List;

public class PlcThread implements Runnable, ConnectionFailListener {

    private PlcConnection plcConnection = new PlcConnection();

    private TagEmail tagEmail = new TagEmail();
    private TagPersist tagPersist = new TagPersist();
    private TagLogger tagLogger = new TagLogger();
    private Plc plc;
    private List<Tag> tags = new ArrayList<>();
    private Scanner scanner;
    private boolean connectionMonitor;

    public PlcThread(Plc plc) {
        this.plc = plc;
        Connection connection = plcConnection.plcConnect(plc); //
        this.scanner = new Scanner(connection, 5, this);
        this.connectionMonitor = true;
    }


    public boolean checkConnection() {
        EtherNetIP netIP = new EtherNetIP(plc.getIp(), plc.getSlot());

        try {
            netIP.connectTcp();
            netIP.close();
            return true;
        } catch (Exception e) {
            System.out.println("Fail to connect! Device description: '" + plc.getDescription() + "' IP: " + plc.getIp());

            e.printStackTrace();
            return false;
        }
    }

    public void scan() {


        for (ItagConfig itagConfig : plc.getItagConfigs()) {
            Tag tag = scanner.add(itagConfig.getTimeUpdate(), itagConfig.getName());
            for (ListenersIndex listenersIndex : itagConfig.getListeners()) {

                if (listenersIndex.equals(ListenersIndex.EMAIL)) {
                    tag.addListener(tagEmail);
                }
                if (listenersIndex.equals(ListenersIndex.LOGGER)) {
                    tag.addListener(tagLogger);
                }
                if (listenersIndex.equals(ListenersIndex.PERSIST)) {
                    tag.addListener(tagPersist);
                }
            }
            this.tags.add(tag);

        }
    }


    @Override
    public void run() {
        boolean b = true;
        System.out.println("Thread " + plc.getDescription() + " has started!");
        scan();
        while (b) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!checkConnection() || !connectionMonitor) {
                b = false;
                this.scanner.stop();
            }
        }
        System.out.println("Thread " + plc.getDescription() + " FINISHED");

    }

    @Override
    public void actionFailure(TagList tagList) {
        System.out.println("Error try out  --------------------------------------------------");

        this.connectionMonitor = false;

    }

    public Plc getPlc() {
        return plc;
    }
}
