import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import userEvent from '@testing-library/user-event';
import Input from './Input';

describe('Input', () => {
  const setUp = () => {
    const mockHandleOnChange = jest.fn();
    const utils = render(<Input
      onChange={(e) => {
        mockHandleOnChange();
      }}
      value=""
      type="email"
      id="email"
      placeholder="Email address"
      dataTestId="email"
      className="input"
    />);
    return {
      ...utils,
      mockHandleOnChange,
    };
  };

  test('should match snapshot', () => {
    setUp();
    expect(screen.getByRole('textbox', { name: '' }))
      .toMatchSnapshot();
  });
  test('pass valid email to test email input field', async () => {
    const {
      mockHandleOnChange,
    } = setUp();
    const email = 'test@mail.com';

    const input = screen.getByRole('textbox', { name: '' });
    await userEvent.type(input, email);

    expect(mockHandleOnChange)
      .toHaveBeenCalled();
  });
});
