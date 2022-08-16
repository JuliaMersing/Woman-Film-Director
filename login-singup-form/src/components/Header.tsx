import React from 'react';
import Link from './Link';

type HeaderProps = {
  heading: string,
  paragraph: string,
  linkName: string,
  href: string,
}

const Header = ({
  heading,
  paragraph,
  linkName,
  href,
}: HeaderProps) => (
  <div className="mb-10">
    <div className="flex justify-center">
      <i className="fa-solid fa-bell-concierge text-[56px] text-purple-600 justify-center" />

    </div>
    <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
      {heading}
    </h2>
    <p className="mt-2 text-center text-sm text-gray-600 mt-5">
      {paragraph}
      <Link className="font-medium text-purple-600 hover:text-purple-500 ml-1" href={href}>
        {linkName}
      </Link>
    </p>
  </div>
);

export default Header;
