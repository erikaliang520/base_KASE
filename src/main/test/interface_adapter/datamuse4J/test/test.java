package interface_adapter.datamuse4J.test;

class test {

    public static void main(String[] args) {
        interface_adapter.datamuse4J.src.datamuse.DatamuseQuery dQuery = new interface_adapter.datamuse4J.src.datamuse.DatamuseQuery();

        String s = dQuery.findAdjByTopicRelativity("soccer", "sport");

        System.out.println(s + "\n\n");
        System.out.println(dQuery.findAdjByTopicRelativity("soccer", "sport"));

    }
}

