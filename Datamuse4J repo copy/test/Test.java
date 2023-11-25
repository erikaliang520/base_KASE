import datamuse.*;
class test{
    public static void main(String[] args) {

        DatamuseQuery dQuery = new DatamuseQuery();

        String s = dQuery.findAdjByTopicRelativity("soccer", "sport");

        System.out.println(s+"\n\n");
        System.out.println(dQuery.findAdjByTopicRelativity("soccer", "sport"));

    }
}