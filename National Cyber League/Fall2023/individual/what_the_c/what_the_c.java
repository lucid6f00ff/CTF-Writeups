import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;//used since we might not know how many ips are being passed in

public class what_the_c{
    //we want the ip address on each line
    private static String getIPAddress(String line) {
        //sets filter to look for an ip address
        String regex = "(sshd XX.*)";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(line);
        
        //looks for the ip address
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        int counter= 0;//counts unique;
        //reads from masscanoutputsorter
        BufferedReader reader = new BufferedReader(new FileReader("what_the_c.txt"));
        //sorts and stores unique ip addresses
        TreeSet<String> uniqueIPs = new TreeSet<>();
        String line;//stores each line in the file

        //[loop] look for ip addresses
        while ((line = reader.readLine()) != null) {
            //gets ip address from each line
            String ipAddress = getIPAddress(line);
            if (ipAddress != null) {
                // System.out.println(getIPAddress(line));
                uniqueIPs.add(ipAddress);
                // counter++;
            }
        }
        reader.close();
        
        // track number of unique ips
        for (String ip : uniqueIPs) {
            //System.out.println(ip);
            counter++;
        }
        //print number of unique ips
        System.out.println(counter+ " names scanned.");
    }
}