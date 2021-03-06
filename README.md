# VisSearch: Framework for Visual Search
Implementation for the Best Student Paper for MAICS2016: http://cslab.valpo.edu/maics2016/paper20.pdf
Live Demonstration (Temporary): http://www.citegraph.xyz:8080/vis


## Installation
This document will assist you in successfully installing the docvis webapp for development.

### PREREQUISITES:
	You have the following: perl installed, a distribution of the repo, intelliJ IDEA, a working knowledge of computers

-----------

### INSTALLATION:

Step 1.) Install necessary perl modules.
     This includes Text::CSV, Text::Unidecode, & WWW::Mechanize.  This operation is complted easiest by using cpanm to download the necessary modules.  Download cpan, then run the following:
     	  $ cpan App::cpanminus
	  $ cpanm Text::CSV
	  $ cpanm Text::Unidecode
	  $ cpanm WWW::Mechanize
     If you have any issues completing this step, google it.

Step 2.) Download the papers
     You may make up any directories of your choosing, but the recommended setup is as follows:
     Create a directory named RESOURCE_FOLDER in the base docvis directory.
     Inside RESOURCE_FOLDER create the path 'resources/pdfs'.
     Inside RESOURCE_FOLDER create a directory 'db'
     Run scripts/getPapersCsv.pl with the following parameters:
     	 $ ./getPapersCsv.pl INPUT_CSV OUTPUT_DIRECTORY DOCUMENT_DATABASE_NAME
     	   Where INPUT_CSV is scripts/pdf-links.csv,
     	   OUTPUT_DIRECTORY is the path to the directory where you will be dumping all of your pdfs (recommended RESOURCE_FOLDER/resources/pdfs),
     	   and DOCUMENT_DATABASE_NAME is '$RESOURCE_FOLDER/db/pdf-info.csv' NOTE: In the vanilla implementation of docvis, this parameter is REQUIRED to be the path to the RESOURCE_FOLDER/db/pdf-info.csv.
     	   NOTE: the script will take a good while to complete; it has to download a lot of data
	   
Step 3.) Create stopwords file
     The stopwords file is a file containing blacklisted words that are NOT to be indexed.  Any word contained within will be skipped over when indexing.
     You may use whatever stopwords you wish, but we recommend using our stopwords file, which you can download for yourself at
          https://bitbucket.org/mcsquared/citegraphweb/downloads/stopwords.txt
     Regardless of what you have for your stopwords file, place it at
     	  RESOURCE_FOLDER/db/stopwords.txt

Step 4.) Create dictionary file
     The dictionary file is a whitelist text file containing words that are to be recognized by the tier 2 utilities: the words in this file are the only words to appear in tier 2 as synonyms, related terms, etc. 
     Like the stopwords.txt, you may use whatever stopwords you wish, but we recommend our dictionary file, which can be found at
     	  https://bitbucket.org/mcsquared/citegraphweb/downloads/dictionary.txt
     Regardless of what you have for your dictionary file, place it at
     	  RESOURCE_FOLDER/db/dictionary.txt

Step 5.) Set up server run configuration
     If you are not using intelliJ, you will have to set up a configuration to run a tomcat server with RESOURCE_FOLDER defined as described earlier.
     For intelliJ: Open up the project.  Choose 'edit configurations' from the run menu.  Select the '+' and choose Maven. Name it something nice, like "Run Tomcat."
     Where it says "command line" enter
     	 tomcat7:run
     Click on the 'Runner' tab.  Under Environment variables click on the '...' button. Click on the '+' and add an entry for
     name:	   RESOURCE_FOLDER
     value: {wherever you parked the RESOURCE_FOLDER directory earlier on.}
     Accept changes.  Accept changes again.  Now select your new configuration from the drop-down run menu, and click on the green 'run' arrow.

------------

### POST-INSTALLATION NOTES:

 If you see the terminal spin up the indexer and start indexing your pdfs, then
    CONGRATULATIONS!!!  You have successfully set up the docvis project.
 To make future instances of the server start up more rapidy, you may add a config file titled
    RESOURCE_FOLDER/config/index-config.cfg
 Where RESOURCE_FOLDER is what you specified earlier.  Add the following line to the file:
    INDEX_DOCS=false

 This will tell the application to skip indexing when it starts the server, making things much quicker for you in the long run.  If you ever want it to index again, simply edit the line to read 'true' instead of 'false'.

----------------