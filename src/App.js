import logo from './logo.svg';
import './App.css';
import MyMapComponent from './Maps';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Travel.io</h1>
      </header>
      <main>
        <MyMapComponent />{/*Iclude map component herre*/}
      </main>
    </div>
  );
}

export default App;
