package interface_adapter.Datamuse4J.test;

class test {

    public static void main(String[] args) {
        datamuse.DatamuseQuery dQuery = new datamuse.DatamuseQuery();

        String s = dQuery.findAdjByTopicRelativity("soccer", "sport");

        System.out.println(s + "\n\n");
        System.out.println(dQuery.findAdjByTopicRelativity("soccer", "sport"));

    }
}

