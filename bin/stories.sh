curl -i -XPOST -H"Content-Type: application/json" localhost:8082/stories -d"{\"projectId\": ${PROJECT_ID}, \"name\": \"Find some reeds\"}"
curl -i localhost:8082/stories?projectId=${PROJECT_ID}
