package servlets.test_servlets;

import api.exception.LuceneSearchException;
import api.term_search.SentenceRelatedTerms;
import common.data.ScoredTerm;
import exception.SearchException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import servlets.servlet_util.RequestUtils;
import servlets.servlet_util.ResponseUtils;
import servlets.servlet_util.ServletConstant;
import term_search.DocumentRelatedTermsSearcher;
import utilities.ListUtils;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * Utilized for searches on related terms. Finds related terms in a document.
 * Created by chris on 10/13/15.
 */
@WebServlet(value = "/related_terms", name = "relatedTermsServlet")
public class RelatedTermsServlet extends GenericServlet {
    private static final Log log = LogFactory.getLog(RelatedTermsServlet.class);
    /**
     * Related Terms Service
     *
     * @param req Required Parameters:
     *            docId: The id of the document that is used as the basis for finding related terms
     *            term: The term to find the related terms for
     *            Optional Parameters:
     *            limit: Limit the number of terms that are returned
     * @param res Response contains a list of Scored Termss
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            int docId;
            if (req.getParameterMap().containsKey("docId")) {
                docId = RequestUtils.getIntegerParameter(req, ServletConstant.DOC_ID);
            } else {
                throw new LuceneSearchException("No document ID.");
            }

            String term;
            if (req.getParameterMap().containsKey("term")) {
                term = req.getParameter("term");
            } else {
                throw new LuceneSearchException("No Term");
            }

            DocumentRelatedTermsSearcher srt = new SentenceRelatedTerms();
            List<ScoredTerm> terms = srt.getDocumentRelatedTerms(docId, term);
            if (req.getParameterMap().containsKey("limit")) {
                int limit = RequestUtils.getIntegerParameter(req, "limit");
                terms = ListUtils.getSublist(terms, limit);
            }

            ResponseUtils.printJsonResponse(res, terms);
        } catch (LuceneSearchException | NumberFormatException e) {
            log.error("Problem with obtaining related terms.", e);
            res.getWriter().println("<h1>ERROR</h1><p>" + e.toString() + "</p>");
        } catch (SearchException e) {
            log.error("There was a problem creating sentence related terms class.");
        }
    }
}
