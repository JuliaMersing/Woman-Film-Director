import React from 'react';

type LinkProps = {
  children: React.ReactNode;
  className: string
  href: string,
}

const Link = ({
  children,
  className,
  href,
}: LinkProps) => (
  <a href={href} className={className}>{children}</a>
);

export default Link;
