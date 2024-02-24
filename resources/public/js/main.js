document.addEventListener('alpine:init', () => {
	Alpine.data('productImages', () => ({
		images: [],

		init() {
			this.images[0] = true;
		},

		show(i) {
			this.images = this.images.map(i => false);
			this.images[i] = true;
		}
	}))
})
