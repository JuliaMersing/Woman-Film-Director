import { verifyEmail, verifyPassword } from './validation';

describe('input form utils', () => {
  const validEmail = ['test@me.com', 'jmersing@gmail.com', 'mduras@yahoo.com'];
  const invalidEmail = ['test@.com', 'jmersing@gmail', 'mduras@.com'];
  const validPassword = ['A1234567', 'margariteDuras', 'SUSANSONTAG'];
  const invalidPassword = ['1234567', 'A234567', 'lauradern'];

  test('should validate that email has the correct format', () => {
    validEmail.forEach((email) => expect(verifyEmail(email))
      .toContain(''));
  });
  test('should validate that email has not the correct format', () => {
    invalidEmail.forEach((email) => expect(verifyEmail(email))
      .toContain('Invalid email provided'));
  });
  test('should validate that password has the correct format', () => {
    validPassword.forEach((password) => expect(verifyPassword(password))
      .toContain(''));
  });
  test('should validate that password has not the correct format', () => {
    invalidPassword.forEach((password) => expect(verifyPassword(password))
      .toContain('Should have minimum 8 characters and 1 upper case'));
  });
});
