import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ToastProvider } from 'react-native-toast-notifications';
import Login from './pages/login';
import SignUp from './pages/signUp';

const App = () => (
  <BrowserRouter>
    <ToastProvider>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signUp" element={<SignUp />} />
      </Routes>
    </ToastProvider>
  </BrowserRouter>

);

export default App;
