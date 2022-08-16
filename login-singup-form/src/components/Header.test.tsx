import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Header from './Header';

describe('Header component', () => {
  test('should navigate to ... when link is clicked', () => {
    render(<Header
      heading="Login to your account"
      paragraph="Don't have an account?"
      linkName="Signup"
      href="/signUp"
    />);
    const link = screen.getByRole('link');
    userEvent.click(link);
    expect(link)
      .toHaveAttribute('href', '/signUp');
  });
});
