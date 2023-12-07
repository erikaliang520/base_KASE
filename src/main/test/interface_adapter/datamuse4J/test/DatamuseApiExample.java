package interface_adapter.datamuse4J.test;

import frameworks_and_drivers.api.datamuse4J.src.datamuse.DatamuseQuery;

class DatamuseApiExample {

    public static void main(String[] args) {
        DatamuseQuery dQuery = new DatamuseQuery();

        String s = dQuery.findAdjByTopicRelativity("soccer", "sport");

        System.out.println(s + "\n\n");
        System.out.println(dQuery.findAdjByTopicRelativity("soccer", "sport"));

    }
}

