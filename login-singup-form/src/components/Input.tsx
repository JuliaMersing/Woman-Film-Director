import React, { FormEvent } from 'react';

type InputProps = {
  value: string;
  onChange: (e: FormEvent) => any
  onBlur?: (e: FormEvent) => any
  type: string,
  id: string,
  placeholder: string,
  dataTestId: string,
  error?: string
  className: string
}

const Input = ({
  value,
  onChange,
  onBlur,
  type,
  id,
  placeholder,
  dataTestId,
  error,
  className,
}: InputProps) => (
  <div className="mb-6">
    <input
      onChange={onChange}
      onBlur={onBlur}
      value={value}
      className={className}
      type={type}
      id={id}
      placeholder={placeholder}
      {...{ 'data-testid': `${dataTestId}-input` }}
    />
    {error && (
      <div className="text-red-600">{error}</div>
    )}
  </div>
);

export default Input;
