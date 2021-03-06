package servlets.endpoint_servlets;

import api.document_search.MultiQuerySearcher;
import api.exception.LuceneSearchException;
import api.reader.LuceneIndexReader;
import com.google.gson.GsonBuilder;
import common.results.MultiQueryResults;
import data_processing.data.D3ConvertibleJson;
import data_processing.multi_query_processing.MultiQueryConverter;
import document_search.MultiQuerySearch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import servlets.servlet_util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Performs a multi-query search. Can be set in vis mode or non-vis mode
 * Created by chris on 11/19/15.
 */
@WebServlet(value = "/multi_term_search", name = "multiTermSearch")
public class MultiQueryServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(MultiQueryServlet.class);
    /**
     * Servlet Service for doing multi query searches
     *
     * @param req Required Parameters:
     *            query*: Queries to be used. This determines what to search for
     *            Optional Parameters:
     *            vis: If this parameter is included, then the output will be in visualization format
     *            num_queries: If this parameter is included, then the number of documents will be no greater than this.
     *              (It will revert to the default number otherwise)
     * @param res JSON representation of the query search.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            List<String> queries = RequestUtils.getQueries(req);
            String[] queryStringArray = new String[queries.size()];
            queryStringArray = queries.toArray(queryStringArray);
            MultiQuerySearch searcher =
                    new MultiQuerySearcher(LuceneIndexReader.getInstance());
            List<MultiQueryResults> queryResults;

            // TODO: This is fugly.  Beautify.
            try {
                 queryResults = req.getParameterMap().containsKey("doc_limit")
                        ? searcher.searchForResults(Integer.parseInt(req.getParameter("doc_limit")), queryStringArray)
                        : searcher.searchForResults(queryStringArray);
            } catch (NumberFormatException ex) {
                // This shouldn't ever happen unless manual modification of the request string occurs.
                log.warn("Bad input given for doc_limit.  What were you thinking?  It has to be a number." +
                        "  Going with the default doc_limit");
                queryResults = searcher.searchForResults(queryStringArray);
            }

            if (req.getParameterMap().containsKey("vis")) {
                D3ConvertibleJson converted = MultiQueryConverter.convertToLinksAndNodes(queryResults);
                res.getWriter().println((new GsonBuilder().setPrettyPrinting()).create().toJson(converted));
            } else {
                res.getWriter().println((new GsonBuilder()).create().toJson(queryResults));
            }


        } catch (LuceneSearchException e) {
            log.error("Error with multi-query servlet", e);
        }
    }



}
