module.exports = {
	content: [
		'./src/**/*',
	],
	theme: {
		extend: {},
	},
	plugins: [
		require('@tailwindcss/forms'),
		require('@tailwindcss/typography'),
		require('@tailwindcss/aspect-ratio'),
	],
}
