import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Checkbox from './Checkbox';

describe('Checkbox component test', () => {
  const setUp = () => {
    const mockHandleRememberChange = jest.fn();

    const utils = render(
      <Checkbox
        onChange={mockHandleRememberChange}
        check={false}
      />,
    );
    return {
      ...utils,
      mockHandleRememberChange,
    };
  };
  test('should match snapshot', () => {
    setUp();
    expect(screen.getByRole('checkbox'))
      .toMatchSnapshot();
  });
  test('should call remember change', async () => {
    const {
      mockHandleRememberChange,
      getByRole,
    } = setUp();

    const remember = await getByRole('checkbox');
    await userEvent.click(remember);
    expect(mockHandleRememberChange)
      .toHaveBeenCalledTimes(1);
  });
  test(
    'should call  rememberCheck when remember checkBox is checked',
    async () => {
      const {
        getByRole,
      } = setUp();

      const remember = await getByRole('checkbox') as HTMLInputElement;
      await userEvent.click(remember);
      expect(remember.checked)
        .toEqual(true);

      await userEvent.click(remember);
      expect(remember.checked)
        .toEqual(false);
    },
  );
});
