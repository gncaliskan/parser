package com.yoncaapp.Model;

import java.util.HashMap;
import java.util.Map;

public class ProxyInfo {
    private String ip;
    private  int port;

    private Map<Integer, ProxyInfo> proxyMap;

    public ProxyInfo() {

    }

    private ProxyInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void createProxyMap(){
        proxyMap = new HashMap<>();
        ProxyInfo p1 = new ProxyInfo("95.137.240.30", 51194);
        proxyMap.put(1, p1);
        ProxyInfo p2 = new ProxyInfo("92.244.36.85", 47150);
        proxyMap.put(2, p2);
        ProxyInfo p3 = new ProxyInfo("93.88.107.125", 34626);
        proxyMap.put(3, p3);
        ProxyInfo p4 = new ProxyInfo("95.87.127.133", 37768);
        proxyMap.put(4, p4);
        ProxyInfo p5 = new ProxyInfo("157.230.173.224", 8118);
        proxyMap.put(5, p5);
        ProxyInfo p6 = new ProxyInfo("134.119.205.242", 8080);
        proxyMap.put(6, p6);
        ProxyInfo p7 = new ProxyInfo("212.72.133.212", 47960);
        proxyMap.put(7, p7);
        ProxyInfo p8 = new ProxyInfo("185.202.165.1", 53281);
        proxyMap.put(8, p8);
        ProxyInfo p9 = new ProxyInfo("134.119.207.12", 1080);
        proxyMap.put(9, p9);
        ProxyInfo p10 = new ProxyInfo("204.15.243.233", 35899);
        proxyMap.put(10, p10);
    }

    public int getProxyMapSize(){
        return proxyMap.size();
    }

    public ProxyInfo getProxyById(int id){
        return proxyMap.get(id);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
