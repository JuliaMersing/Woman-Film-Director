import React from 'react';
import { render, screen } from '@testing-library/react';
import Link from './Link';

describe('Link component', () => {
  test('should match with snapshot', () => {
    render(<Link href="/#" className="link">
      Forgot
      password?
    </Link>);
    screen.getByRole('link');
    expect('link')
      .toMatchSnapshot();
  });
});
