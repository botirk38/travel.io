import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlane, faCompass } from '@fortawesome/free-solid-svg-icons';

const Home = ({ userName = 'User' }) => {
  return (
    <div className="container">
      <header>
        <h1>Welcome to Travel.io</h1>
      </header>
      <div className="greeting">
        <h2>Hi {userName}, what are you feeling like today?</h2>
      </div>
      <div className="card-container">
        <div className="card">
          <FontAwesomeIcon icon={faPlane} size="3x" />
          <h3>Find the Cheapest Route</h3>
          <p>Discover the most cost-effective way to your exact destination.</p>
        </div>
        <div className="card">
          <FontAwesomeIcon icon={faCompass} size="3x" />
          <h3>No Idea Where to Go?</h3>
          <p>Explore destinations and find your perfect adventure.</p>
        </div>
      </div>
      <style jsx>{`
        .container {
          text-align: center;
        }
        .card-container {
          display: flex;
          justify-content: center;
          gap: 20px;
          margin-top: 20px;
        }
        .card {
          border: 1px solid #ccc;
          padding: 20px;
          border-radius: 8px;
          width: 300px;
        }
        // Add more styling as needed
      `}</style>
    </div>
  );
};

export default Home;
