import React, { useRef, useState, useEffect } from 'react';
import { GoogleMap, LoadScript, DirectionsRenderer, useJsApiLoader } from '@react-google-maps/api';

const containerStyle = {
  width: '100%',
  height: '400px'
};

const initialCenter = { lat: 51.5074, lng: -0.1278 };
const destination = { lat: 40.4168, lng: -3.7038 }; // Madrid

function MyMapComponent() {
  const mapRef = useRef(null);
  const [directions, setDirections] = useState(null);
  const [isMapLoaded, setIsMapLoaded] = useState(false);

  const { isLoaded, loadError } = useJsApiLoader({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY,
    libraries: ['places'],
  });

  const calculateRoute = () => {
    if (!mapRef.current || !isLoaded) return;

    const directionsService = new window.google.maps.DirectionsService();
    const origin = initialCenter; // London

    directionsService.route(
      {
        origin: origin,
        destination: destination,
        travelMode: window.google.maps.TravelMode.DRIVING,
      },
      (result, status) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
          setDirections(result);
        } else {
          console.error(`Error fetching directions: ${status}`);
        }
      }
    );
  };

  useEffect(() => {
    if (isMapLoaded) {
      calculateRoute();
    }
  }, [isMapLoaded]);

  const onLoad = map => {
    mapRef.current = map;
    setIsMapLoaded(true);
  };

  const onUnmount = () => {
    mapRef.current = null;
  };

  if (loadError) {
    return <div>Error loading Google Maps</div>;
  }

  return (
    <div className="container">
      <div className="map-container">
        <LoadScript
          googleMapsApiKey={process.env.REACT_APP_GOOGLE_MAPS_API_KEY}
          libraries={['places']}
        >
          <GoogleMap
            mapContainerStyle={containerStyle}
            center={initialCenter}
            zoom={10}
            onLoad={onLoad}
            onUnmount={onUnmount}
          >
            {directions && <DirectionsRenderer directions={directions} />}
          </GoogleMap>
        </LoadScript>
      </div>
      <div className="content">
        {/* Content below the map */}
        <p>This is additional content below the map.</p>
      </div>
    </div>
  );
}

export default MyMapComponent;
