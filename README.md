### Coding task
A coding task for an interview. I had to write an app to do a search on Google Books API and return the result in the GUI in
2 hours. The following is the result. The UI is complete crap (just a list of RecycleView Cards) since that was not the focus of test. 

NOTE: I deactived the API key as of now, so it is not going to work until you add a real Google Books API key and recompile.

### External Dependencies

* Volley - Volley is the HTTP library used for networking. It handles both the REST GET call
and the image download. Volley handles the network scheduling, background threading
and caching. 

### Potential Improvements
If I had more time I would improve the app the following ways:
1. Put the google api key in gradle.properties then use BuildConfig to access the key api during run time.
in build.gradle I can then build with a different api key depending on the flavor of the build 
(release/debug etc)
2. Add unit testings
3. Add handling for different screen sizes and/or layout depending on the requirements. 
For example, we can load 2 fragments for a Master/Details layout from MainActivity for a large tablet.
3. More error handling for network failure and more error notification so users can figured out what
went wrong.
4. Implement pagination. The hard coded query API only returns the first 40 results. I should implement
the pagination API if I have more time so we get a complete list of the results.


