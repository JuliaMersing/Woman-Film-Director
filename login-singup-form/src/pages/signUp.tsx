import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useToast } from 'react-native-toast-notifications';
import { newUser } from '../redux/registerSlice';
import { verifyEmail, verifyPassword } from '../utils/validation';
import Input from '../components/Input';
import Checkbox from '../components/Checkbox';
import Button from '../components/Button';
import Header from '../components/Header';

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [remember, setRemember] = useState(true);
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [passwordConfirmError, setPasswordConfirmError] = useState('');
  const validSignUp = passwordError === '' && emailError === '' && passwordConfirmError === '';

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

  const handlePasswordConfirm = (event: any): void => {
    const confirmedPassword = event.target.value;
    setConfirmPassword(confirmedPassword);
  };

  const onFormSubmit = (event: any): void => {
    event.preventDefault();
    if (!validSignUp) {
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

  const handlePasswordConfirmBlur = (): void => {
    if (password !== confirmPassword) {
      setPasswordConfirmError('Password don\'t match');
    } else {
      setPasswordConfirmError('');
    }
  };

  return (
    <div
      className="min-h-full h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8"
    >
      <div className="max-w-md w-full space-y-8">
        <Header
          heading="Sign Up to create an account"
          paragraph="Already have an account?"
          linkName="Login"
          href="/"
        />
        <form onSubmit={onFormSubmit} data-testid="login-form">
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
          <Input
            onChange={handlePasswordConfirm}
            onBlur={handlePasswordConfirmBlur}
            value={confirmPassword}
            className={passwordError ? 'input-error' : 'input'}
            type="password"
            id="password"
            placeholder="Confirm password"
            dataTestId="password"
            error={passwordConfirmError}
          />
          <div className="flex-space-between">
            <div className="flex-center ">
              <Checkbox onChange={handleRememberChange} check={remember} />
              <p className="text-gray-800 ml-2">Remember me </p>
            </div>
          </div>
          <div className="text-center-left">
            <Button> Sign Up </Button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
