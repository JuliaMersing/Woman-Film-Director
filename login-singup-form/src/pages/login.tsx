import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useToast } from 'react-native-toast-notifications';
import Input from '../components/Input';
import { newUser } from '../redux/registerSlice';
import Button from '../components/Button';
import Link from '../components/Link';
import Checkbox from '../components/Checkbox';
import { verifyEmail, verifyPassword } from '../utils/validation';
import Header from '../components/Header';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [remember, setRemember] = useState(true);
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const validLogin = passwordError === '' && emailError === '';

  const dispatch = useDispatch();

  const toast = useToast();

  const handleEmailChange = (event: any): void => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: any): void => {
    setPassword(event.target.value);
  };

  const handleRememberChange = (event: any): void => {
    const { checked } = event.target;
    setRemember(checked);
  };

  const onFormSubmit = (event: any): void => {
    event.preventDefault();

    if (!validLogin) {
      toast.show('Form has errors', {
        type: 'danger',
        placement: 'top',
      });
      return;
    }
    dispatch(newUser({
      email,
      password,
    }));
  };

  const handleEmailBlur = (event: any): void => {
    const error = verifyEmail(event.target.value);
    setEmailError(error);
  };

  const handlePasswordBlur = (event: any): void => {
    const error = verifyPassword(event.target.value);
    setPasswordError(error);
  };

  return (
      <div
          className="container-page"
      >
        <div className="container-form">
          <form onSubmit={onFormSubmit} data-testid="login-form">
            <Header
                heading="Login to your account"
                paragraph="Don't have an account?"
                linkName="Signup"
                href="/signUp"
            />
            <Input
                onChange={handleEmailChange}
                onBlur={handleEmailBlur}
                value={email}
                className={emailError ? 'input-error' : 'input'}
                type="email"
                id="email"
                placeholder="Email address"
                dataTestId="email"
                error={emailError}
            />
            <Input
                onChange={handlePasswordChange}
                onBlur={handlePasswordBlur}
                value={password}
                className={passwordError ? 'input-error' : 'input'}
                type="password"
                id="password"
                placeholder="Password"
                dataTestId="password"
                error={passwordError}
            />
            <div className="flex-space-between">
              <div className="flex-center ">
                <Checkbox onChange={handleRememberChange} check={remember} />
                <p className="text-gray-800 ml-1">Remember me </p>
              </div>
              <Link href="/#" className="link">
                Forgot
                password?
              </Link>
            </div>
            <div className="text-center-left">
              <Button>
                Login
              </Button>
            </div>
          </form>
        </div>
      </div>
  );
};

export default Login;
