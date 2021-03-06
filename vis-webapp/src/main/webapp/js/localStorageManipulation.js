/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Chris Bellis, Chris Perry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * Created by Chris on 3/24/2016.
 * Shared functions that are involved in manipulating the localStorage
 */

/**
 * Prepares the local storage for a search.
 */
function loadFieldsIntoLocalStorage() {
    var i = 1;
    while(null != document.getElementById("query_text"+i)){
        localStorage.setItem("query"+i, document.getElementById("query_text"+i).value);
        i++;
    }
    // Make sure we have a limit before we try and grab it
    if (null != document.getElementById("limit")) {
        localStorage.setItem("doc_limit", document.getElementById("limit").value);
    }
}

/**
 * Pulls the required fields out of storage and into a string that is returned.
 */
function getVisQueryString() {
    return getQueryString(true);
}

/**
 * Pulls the required fields out of storage and into a string that is returned.
 */
function getTextQueryString() {
    return getQueryString(false);
}

/**
 * Pulls the required fields out of storage and into a string that is returned.
 */
function getQueryString(is_vis) {
    var query_string = "multi_term_search?";
    if (is_vis) {
        query_string += "vis"
    }
    var i = 1;
    while (null != localStorage.getItem("query" + i)) {
        var query = localStorage.getItem("query" + i);
        query_string += "&query" + i + "=" + query;
        i++;
    }
    query_string += "&doc_limit=" + localStorage.getItem("doc_limit");
    return query_string;
}

/**
 * Grab info from the fields and conduct the search.
 */
function updateQueriesAndDoSearch() {
    loadFieldsIntoLocalStorage();
    doSearch();
}
