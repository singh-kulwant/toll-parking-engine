Park Vehicle:

```curl
curl -X POST \
-H "Content-Type: application/json" \
-d '{"plate_number": "ABC123", "vehicle_type": "car", "color": "red", "brand": "Toyota", "slot_number": 1}' \
http://localhost:9090/api/parking-lot/park
```

Unpark Vehicle:

```curl
curl -X POST \
     -H "Content-Type: application/json" \
     -d '{"plate_number": "ABC123"}' \
     http://localhost:9090/api/parking-lot/unpark
```

Generate Bill:

```curl
curl -X POST \
     http://localhost:9090/api/parking-lot/generate-bill?plate-number=ABC123
```

Fetch Current Parking Status:

```curl
curl http://localhost:9090/api/parking-lot/parking-status
```

Search Vehicle by Plate Number:

```curl
curl http://localhost:9090/api/parking-lot/search-vehicle?plate-number=ABC123
```