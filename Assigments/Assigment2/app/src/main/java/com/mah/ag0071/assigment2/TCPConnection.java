package com.mah.ag0071.assigment2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPConnection extends Service {

    public static final String IP ="IP",PORT ="PORT";
    private String ip;
    private int connectionPort;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private InetAddress inetAddress;
    private TCPConnection connection;
    private RunOnThread thread;
    private Buffer<String> reciveBuffer;
    private Receive receive;
    private boolean connected;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Connection","On start service");
        this.ip = intent.getStringExtra(IP);
        this.connectionPort = Integer.parseInt(intent.getStringExtra(PORT));
        connected = false;
        thread = new RunOnThread();
        reciveBuffer = new Buffer<>();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("Connection","On bind service");
        connect();
        return new LocalService();

    }

    public void connect(){
        Log.v("Connection","In connect");
        thread.start();
        Log.v("Connection","After thread start");
        thread.execute(new Connect());
    }

    public void disconnect(){
        thread.execute(new Disconnect());
    }

    public void send(String msg){
        thread.execute(new Send(msg));
    }

    public String receive() throws InterruptedException{
        return reciveBuffer.get();
    }

    public boolean isConnected(){
        return connected;
    }

    public class LocalService extends Binder{
        public TCPConnection getService(){
            return TCPConnection.this;
        }
    }

    private class Connect implements Runnable{

        @Override
        public void run() {
            try{
                Log.v("Connection","In thread connection run");
                inetAddress = InetAddress.getByName(ip);
                Log.v("Connection","InetAddre: " + InetAddress.getByName(ip));
                Log.v("Connection","In thread socket");
                socket = new Socket(inetAddress,connectionPort);
                Log.v("Connection","In thread input");
                input = new DataInputStream(socket.getInputStream());
                Log.v("Connection","In thread output");
                output = new DataOutputStream(socket.getOutputStream());
                Log.v("Connection","In thread flush");
                output.flush();
                Log.v("Connection","In thread recieve");
                //reciveBuffer.put("Connected");
                receive = new Receive();
                Log.v("Connection","In thread recieve start");
                receive.start();
                Log.v("Connection","Connection Succeded");
                connected = true;
            } catch (UnknownHostException e) {
                e.printStackTrace();
                Log.v("Connection",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("Connection",e.toString());
            }
        }
    }

    private class Disconnect implements Runnable{

        @Override
        public void run() {
            try{
                if(input != null){
                    input.close();
                }
                if (output != null){
                    output.close();
                }
                if (socket != null){
                    socket.close();
                }
                if (receive != null){
                    receive = null;
                }
                thread.stop();
                Log.v("Connection","Connection closed sucessfully?");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Receive extends Thread{

        public void run(){
            String result;
            try{
                while (receive != null){
                    result = input.readUTF();
                    reciveBuffer.put(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Send implements Runnable{

        private String msg;

        public Send(String msg){
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                Log.v("Connection","Message to server: " + msg);
                output.writeUTF(msg);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
