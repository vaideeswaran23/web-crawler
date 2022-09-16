# Web Crawler
Given the list of urls and a search text, the web crawler will crawl through the urls and find the count of matched search word in each url.

## To start the project

```
mvn spring-boot:run
```

## API Description
### Request

`POST /api/crawl`

    curl --location --request POST 'http://localhost:8080/api/crawl' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "urls": [
    "https://martinfowler.com/architecture/"
    ],
    "searchText": "architecture"
    }'

### Response

    [
        {
            "url": "https://martinfowler.com/architecture/",
            "count": 81,
            "message": "Successfully parsed"
        }
    ]
