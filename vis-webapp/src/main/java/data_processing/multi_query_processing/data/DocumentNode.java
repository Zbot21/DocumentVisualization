package data_processing.multi_query_processing.data;

import data_processing.data.Node;

/**
 * Document node, this node contains a document.
 * Position is not set, but determined dynamically by the simulation.
 * Created by chris on 11/22/15.
 */
public class DocumentNode extends Node {
    public final int docId;
    public final double size;
    public final double score;

    public DocumentNode(String name, int id, String color, int docId, double size, double score) {
        super(false, name, id, color);
        this.docId = docId;
        this.size = size;
        this.score = score;
    }

    /**
     * Creates a document node with the given parameters
     *
     * @param name
     * @param id
     * @param color
     * @param docId
     * @param size
     * @param score
     * @return new Document node
     */
    public static DocumentNode of(String name, int id, String color, int docId, double size, double score) {
        return new DocumentNode(name, id, color, docId, size, score);
    }
}
