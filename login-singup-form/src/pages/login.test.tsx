import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Login from './login';

const mockUseDispatch = jest.fn();
jest.mock('react-redux', () => ({
  ...jest.requireActual('react-redux'),
  useDispatch: () => mockUseDispatch,
}));

describe('Login', () => {
  const setUp = () => {
    const utils = render(<Login />);

    const loginButton = screen.getByRole('button');
    const rememberCheck = screen.getByRole('checkbox');
    return {
      ...utils,
      loginButton,
      rememberCheck,
    };
  };

  test('should render component and match snapshot', () => {
    const { container } = render(<Login />);
    expect(container)
      .toMatchSnapshot();
  });
  test(
    'should call onSubmit when button is clicked',
    async () => {
      const {
        loginButton,
      } = setUp();

      await userEvent.click(loginButton);

      expect(mockUseDispatch)
        .toHaveBeenCalledTimes(1);
    },
  );
});
