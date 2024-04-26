Park Vehicle:

```bash
curl -X POST \
-H "Content-Type: application/json" \
-d '{"plateNumber": "ABC123", "vehicleType": "gasoline", "color": "red", "brand": "Toyota"}' \
http://localhost:9090/api/park
```

Unpark Vehicle:

```bash
curl -X POST \
     -H "Content-Type: application/json" \
     -d '{"plateNumber": "ABC123", "slotNumber": "car_slot_1"}' \
     http://localhost:9090/api/unpark
```

Generate Bill:

```bash
curl -X POST \
     http://localhost:9090/api/generate-bill?plate-number=ABC123
```

Fetch Current Parking Status:

```bash
curl http://localhost:9090/api/parking-status
```

Search Vehicle by Plate Number:

```bash
curl http://localhost:9090/api/search-vehicle?plate-number=ABC123
```

Get Parking Slot Details::

```bash
curl http://localhost:9090/api/parking-slot/GASOLINE_slot_1
```