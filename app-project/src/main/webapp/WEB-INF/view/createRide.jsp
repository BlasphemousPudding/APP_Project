<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Ride</title>
    <!-- Include Google Maps API -->
    <script src="https://maps.googleapis.com/maps/api/js?key=3d1798bb3c6ad9e84dd74ce46e1b3b6f&libraries=places"></script>
    <script>
        function initMap() {
            var originInput = document.getElementById('origin');
            var destinationInput = document.getElementById('destination');

            var options = { componentRestrictions: { country: "us" } }; // Adjust to your country

            var originAutocomplete = new google.maps.places.Autocomplete(originInput, options);
            var destinationAutocomplete = new google.maps.places.Autocomplete(destinationInput, options);

            originAutocomplete.addListener('place_changed', function() {
                var place = originAutocomplete.getPlace();
                document.getElementById('origin_latitude').value = place.geometry.location.lat();
                document.getElementById('origin_longitude').value = place.geometry.location.lng();
            });

            destinationAutocomplete.addListener('place_changed', function() {
                var place = destinationAutocomplete.getPlace();
                document.getElementById('destination_latitude').value = place.geometry.location.lat();
                document.getElementById('destination_longitude').value = place.geometry.location.lng();
            });
        }
    </script>
</head>
<body onload="initMap()">
    <h2>Create a New Ride</h2>
    <form action="createRide" method="post">
        <label for="origin">Origin:</label>
        <input type="text" id="origin" name="origin" required>
        <input type="hidden" id="origin_latitude" name="origin_latitude">
        <input type="hidden" id="origin_longitude" name="origin_longitude">

        <label for="destination">Destination:</label>
        <input type="text" id="destination" name="destination" required>
        <input type="hidden" id="destination_latitude" name="destination_latitude">
        <input type="hidden" id="destination_longitude" name="destination_longitude">

        <label for="date_time">Date & Time:</label>
        <input type="datetime-local" id="date_time" name="date_time" required>

        <label for="available_seats">Available Seats:</label>
        <input type="number" id="available_seats" name="available_seats" required>

        <label for="comments">Comments:</label>
        <textarea id="comments" name="comments"></textarea>

        <button type="submit">Create Ride</button>
    </form>
</body>
</html>
