import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SignUp from './signUp';

const mockUseDispatch = jest.fn();
jest.mock('react-redux', () => ({
  ...jest.requireActual('react-redux'),
  useDispatch: () => mockUseDispatch,
}));

describe('signUp component', () => {
  const setUp = () => {
    const utils = render(<SignUp />);

    const signUpButton = screen.getByRole('button');
    const rememberCheck = screen.getByRole('checkbox');
    return {
      ...utils,
      signUpButton,
      rememberCheck,
    };
  };
  test('should render component and match snapshot', () => {
    const { container } = render(<SignUp />);
    expect(container)
      .toMatchSnapshot();
  });
  test(
    'should call onSubmit when button is clicked',
    async () => {
      const {
        signUpButton,
      } = setUp();

      await userEvent.click(signUpButton);

      expect(mockUseDispatch)
        .toHaveBeenCalledTimes(1);
    },
  );
});
