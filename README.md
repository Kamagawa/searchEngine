# searchEngine
This is a search engine that crawls to a given site, filters its url, and continue crawling to new sites.
Meanwhile, the search engine will index each page for its parent URL, so https://www.google.ca/?gfe_rd=cr&ei=5k3ZWJ3lLYmN8Qfzl6CQAw&gws_rd=ssl will be indexed as "https://www.google.com". 

### This project is used in conjuction with [mmdb generator](https://github.com/Kamagawa/geoIP2-mmdb-generator) to crawl the database for IP and address queries. 
