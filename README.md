Run Instructions:
Install java , compile and run from the terminal. Reference: https://www.freecodecamp.org/news/how-to-execute-and-run-java-code/

Question Related:
Follow up questions
If a port/protocol does not match any tag, should it default to "Untagged"?
What are the delimiters used in the flow log and lookup file? Defaulting to space for logs and comma for lookup.
Should the output be ordered or is any order fine?

Assumptions
Ports/protocols not found in the lookup will be marked "Untagged".
Flow logs are space-delimited and lookup table is comma-delimited.
Sorting of output isn't required unless specified.

Approach 
Use a dictionary to map port/protocol combinations to tags and another dictionary to keep track of counts.

Alternatives
Another possibility might be using a list to store pairs and manually checking matches, but this would be inefficient.

Algorithm
Utilize a HashMap for mapping port/protocol to tags and another HashMap for counting occurrences.

Alternatives
A List-based approach for linear scan and match, but it would be significantly slower given large data sets.

Details
Load the lookup table into a HashMap with keys as tuples of (port, protocol).
Use two HashMaps—one for counting tags and another for port/protocol combinations.
For each log entry:
Extract destination port and protocol.
Check the tag map for a match; default to "Untagged" if none found.
Increment respective counts in both HashMaps.


Time complexity: O(n + m)

Space Complexity: O(m + p)

The time complexity is O(n + m) because we read through each log entry and lookup mapping once, where n is the number of log entries and m is the number of entries in the lookup table. The space complexity is O(m + p), where m accounts for the lookup table entries and p for the unique port/protocol combinations encountered.
