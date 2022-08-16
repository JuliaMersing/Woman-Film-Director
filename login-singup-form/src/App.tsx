import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './pages/login';

const App = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login />} />
    </Routes>
  </BrowserRouter>

);

export default App;
