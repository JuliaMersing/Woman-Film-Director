module.exports = {
  mode: 'jit',
  content: ['./src/**/*.{html,jsx,tsx,css}', './node_modules/tw-elements/dist/js/**/*.js'],
  plugins: [
    // eslint-disable-next-line global-require
    require('tw-elements/dist/plugin'),
  ],
};
