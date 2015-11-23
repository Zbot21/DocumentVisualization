package data_processing.multi_query_processing.multi_query_json_data;

/**
 * Created by chris on 11/22/15.
 */
public abstract class GenericNode {
    public final boolean fixed;
    public final String name;
    public final int id;
    public final String color;
    public GenericNode(boolean fixed, String name, int id, String color){
        this.fixed = fixed;
        this.name = name;
        this.id = id;
        this.color = color;
    }
}
