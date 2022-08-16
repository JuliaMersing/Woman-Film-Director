import React from 'react';
import { render, screen } from '@testing-library/react';
import Button from './Button';

describe('Button component', () => {
  test('should match snapshot', () => {
    render(<Button> Login </Button>);
    screen.getByRole('button', { name: /Login/i });
    expect('button')
      .toMatchSnapshot();
  });
});
