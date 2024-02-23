document.addEventListener('alpine:init', () => {
	Alpine.data('productImages', (len = 0) => ({
		images: [],

		init() {
			for (i = 0; i < len; i++) {
				this.images[i] = i === 0;
			}
		},

		show(i) {
			this.images = this.images.map(i => false);
			this.images[i] = true;
		}
	}))
})
