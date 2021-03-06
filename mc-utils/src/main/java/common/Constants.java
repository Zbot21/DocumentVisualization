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

package common;

/**
 * Stores constants for the program. This should become a configuration loader someday.
 * Created by Chris on 8/19/2015.
 */
public class Constants {
    public static final String INDEX_DIRECTORY = "indexes";
    public static final String STOPWORDS_FILE = "db/stopwords.txt";
    public static final String DICTIONARY_FILE = "db/dictionary.txt";
    public static final String INDEX_CONFIG_FILE = "config/index-config.cfg";
    public static final String FIELD_PATH = "path";
    public static final String FIELD_CONTENTS = "contents";
    public static final String FIELD_MODIFIED = "modified";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_AUTHOR = "author";
    public static final String FIELD_CONFERENCE = "conference";
    public static final String RESOURCE_FOLDER_VAR = "RESOURCE_FOLDER";
    // These are relative to the document resources directory
    static public final String CSV_LOCATION = "db/pdf-info.csv";

    static public final int MAX_CACHE_SIZE = 10000;

    // Private constructor so you cant create this class
    private Constants() {
    }
}
