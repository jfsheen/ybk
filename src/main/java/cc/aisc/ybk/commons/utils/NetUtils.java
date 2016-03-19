package cc.aisc.ybk.commons.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.net.telnet.TelnetClient;

import org.slf4j.Logger;

/**
 * Created by sjf on 15-11-14.
 */
public class NetUtils {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NetUtils.class);

    /**
     * ping ip并返回结果
     * @param ip
     * @return
     * 描述apache-common-exec 示例
     */
    public static String ping(String ip) {
        String encode = "GBK";
        String os = System.getProperties().getProperty("os.name");
        if(os.toLowerCase().indexOf("windows") == -1){
            encode = "UTF-8";
        }

        try {
            String command = "ping " + ip;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            CommandLine commandline = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(commandline);
            String out = outputStream.toString(encode);
            String error = errorStream.toString(encode);
            return out + error;
        } catch (Exception e) {
            LOGGER.error("ping task failed.", e);
            return e.toString();
        }
    }

    /**
     * 验证网络是否畅通
     * @param ip
     * @param port
     * @param timeout
     * @return
     * 描述：apache-common-net 示例
     */
    public static boolean telnet(String ip, int port, int timeout){
        TelnetClient tc = null;
        try {
            tc = new TelnetClient();
            tc.setConnectTimeout(timeout);
            tc.connect(ip, port);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if(null != tc){
                try {
                    tc.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 验证网络是否畅通
     * @param ip
     * @param port
     * @param timeout
     * @return
     */
    public static boolean socket(String ip, int port, int timeout){
        Socket socket = null;
        try {
            socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress(ip, port);
            socket.connect(addr, timeout);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if(null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
