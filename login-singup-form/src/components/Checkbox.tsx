import React, { FormEvent } from 'react';

type CheckboxProps = {
  onChange: (e: FormEvent) => any,
  check: boolean,
}

const Checkbox = ({
  onChange,
  check,
}: CheckboxProps) => (
  <input
    type="checkbox"
    className="checkbox"
    defaultChecked={check}
    onChange={onChange}
  />
);

export default Checkbox;
