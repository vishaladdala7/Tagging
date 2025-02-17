// Follow up questions
// If a port/protocol does not match any tag, should it default to "Untagged"?
// What are the delimiters used in the flow log and lookup file? Defaulting to space for logs and comma for lookup.
// Should the output be ordered or is any order fine?

// Assumptions
// Ports/protocols not found in the lookup will be marked "Untagged".
// Flow logs are space-delimited and lookup table is comma-delimited.
// Sorting of output isn't required unless specified.

// Approach 
// Use a dictionary to map port/protocol combinations to tags and another dictionary to keep track of counts.

// Alternatives
// Another possibility might be using a list to store pairs and manually checking matches, but this would be inefficient.

// Algorithm
// Utilize a HashMap for mapping port/protocol to tags and another HashMap for counting occurrences.

// Alternatives
// A List-based approach for linear scan and match, but it would be significantly slower given large data sets.

// Details
// Load the lookup table into a HashMap with keys as tuples of (port, protocol).
// Use two HashMaps—one for counting tags and another for port/protocol combinations.
// For each log entry:
// Extract destination port and protocol.
// Check the tag map for a match; default to "Untagged" if none found.
// Increment respective counts in both HashMaps.


import java.util.*;

public class FlowLogParser {
    public static void parseFlowLogs(List<String> flowLogs, Map<String, String> lookupTable) {
        Map<String, Integer> tagCounts = new HashMap<>();
        Map<String, Integer> portProtocolCounts = new HashMap<>();

        for (String log : flowLogs) {
            String[] parts = log.split("\\s+");
            int dstPort = Integer.parseInt(parts[5]);
            int protocolNum = Integer.parseInt(parts[6]);
            String protocol = protocolNum == 6 ? "tcp" : protocolNum == 17 ? "udp" : "unknown";

            String lookupKey = dstPort + "," + protocol;
            String tag = lookupTable.getOrDefault(lookupKey, "Untagged");

            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);

            portProtocolCounts.put(lookupKey, portProtocolCounts.getOrDefault(lookupKey, 0) + 1);
        }

        System.out.println("Tag Counts: " + tagCounts);
        System.out.println("Port/Protocol Combination Counts: " + portProtocolCounts);
    }

    public static void main(String[] args) {
        List<String> flowLogs = Arrays.asList(
            "2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK",
            "2 123456789012 eni-4d3c2b1a 192.168.1.100 203.0.113.101 23 49154 6 15 12000 1620140761 1620140821 REJECT OK",
            "2 123456789012 eni-5e6f7g8h 192.168.1.101 198.51.100.3 25 49155 6 10 8000 1620140761 1620140821 ACCEPT OK"
        );

        Map<String, String> lookupTable = new HashMap<>();
        lookupTable.put("25,tcp", "sv_P1");
        lookupTable.put("443,tcp", "sv_P2");
        lookupTable.put("23,tcp", "sv_P1");

        parseFlowLogs(flowLogs, lookupTable);
    }
}


// Time complexity: O(n + m)

// Space Complexity: O(m + p)

// The time complexity is O(n + m) because we read through each log entry and lookup mapping once, where n is the number of log entries and m is the number of entries in the lookup table. The space complexity is O(m + p), where m accounts for the lookup table entries and p for the unique port/protocol combinations encountered.