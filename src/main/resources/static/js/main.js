/**
* Template Name: NiceAdmin
* Updated: Mar 09 2023 with Bootstrap v5.2.3
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/
(function() {
  "use strict";

  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }


  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener 
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Xử lý đóng, mở modal đăng nhập
   */

  if (select('.auth-btn', true)) {
    on('click', '.auth-btn', function(e) {
      console.log("hih")
      select('.auth-modal').classList.add('auth-modal--open')
      e.stopPropagation();
    }, true)
  }

  /**
   * Ẩn hiên mật khẩu
   */
  if (select('.modal__pass-btn')) {
    on('click', '.modal__pass-btn', function(e) {
      // alert("haha")
      let pass = document.getElementById('');
      if(pass.type == 'text') {
        select('.modal__pass-btn').innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
        pass.type = 'password';
      } else { 
        select('.modal__pass-btn').innerHTML = '<i class="fa-regular fa-eye"></i>';
        pass.type = 'text';
      }
    })
  }

  /**
   * Xử lý mở trang xem chi tiết
   */
  let productArrs = select('.product__item', true)
  productArrs.forEach(item => {
    item.addEventListener('click', (e) => {
      window.location="http://127.0.0.1:5500/product-detail.html";
      // e.stopPropagation();
    })
  })


  /**
   * Xử lý active trong xem chi tiết
   */
  let listItem = select('.list-item-value', true)
  listItem.forEach(item => {
    item.addEventListener('click', () => {
      if(!item.classList.contains('disabled')) {
        let parent = item.parentElement.parentElement;
        parent.querySelector('.active').classList.remove('active');
        item.classList.add('active');
        parent.dataset.value = item.innerText;
      }
      
    })
  })

  /**
   * Xử lý active trong xem profile
   */
  function activeNav(item) {
    let profileRight = select('.profile__right');
    profileRight.querySelector('.showRight').classList.remove('showRight');
    profileRight.querySelector(item.dataset.right).classList.add('showRight');
    item.parentElement.parentElement.querySelector('.active').classList.remove('active');
    item.classList.add('active');
  }

  let navProfileArrs = select('.js-nav-item', true)
  navProfileArrs.forEach(item => {
    item.addEventListener('click', () => {
      activeNav(item);
    })
  })


  let navLinkArrs = select('.js-nav-link', true)

  if(navLinkArrs[0]) {
    navLinkArrs[0].onclick = function() {
      navLinkArrs[0].nextElementSibling.classList.toggle('hide')
    }
  }
  

  let navLinkArrsActive = navLinkArrs[0];
  navLinkArrs.forEach(item => {
    item.addEventListener('click', () => {
      if(navLinkArrsActive.contains(navLinkArrs[0])) {
        navLinkArrsActive.nextElementSibling.classList.add('collapse');
      }
      navLinkArrsActive.classList.add('collapsed');
      item.classList.remove('collapsed');
      if(item.contains(navLinkArrs[0])) {
        item.nextElementSibling.classList.remove('collapse');
        activeNav(item.nextElementSibling.children[0].children[0])
      }
      navLinkArrsActive = item;
      let profileRight = select('.profile__right');
      profileRight.querySelector('.showRight').classList.remove('showRight');
      profileRight.querySelector(item.dataset.right).classList.add('showRight');
      // item.classList.add('active');
    })
  })

  /**
   * Xử lý hiển thị lựa chọn trong cart__product-need-value
   */

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
      select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Sidebar filter
   */
  if (select('.filter__icon-btn')) {
    on('click', '.filter__icon-btn', function(e) {
      select('.filter').classList.toggle('toggle-filter')
    })
  }

  let filter_btn = select('.filter__icon-btn')
  let warn = select('#product .row')

  if (filter_btn && warn) {
    
    const toggleShow = () => {
      let gap = warn.offsetTop - window.scrollY
      if (gap <= 200) {
        filter_btn.classList.add('filter__icon-show')
      } else {
        filter_btn.classList.remove('filter__icon-show')
        // select('.filter').classList.remove('toggle-filter')
      }
    }
    window.addEventListener('load', toggleShow)
    onscroll(document, toggleShow)
  }

  /**
   * Search bar toggle
   */
  if (select('.header__search-toggle')) {
	  on('click', '.header__search-toggle', function(e) {
		select('.header__search').classList.toggle('header__search-show');
	  })
	}
  if (select('.header__search-return')) {
	  on('click', '.header__search-return', function(e) {
		select('.header__search').classList.remove('header__search-show')
	  })
	}

    /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
    let selectHeader = document.querySelector('#header')
    if (selectHeader) {
      const headerScrolled = () => {
        if (window.scrollY > 100) {
          selectHeader.classList.add('header-scrolled')
        } else {
          selectHeader.classList.remove('header-scrolled')
        }
      }
      window.addEventListener('load', headerScrolled)
      onscroll(document, headerScrolled)
    }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Hero carousel indicators
   */
  let heroCarouselIndicators = select("#hero-carousel-indicators")
  let heroCarouselItems = select('#heroCarousel .carousel-item', true)

  heroCarouselItems.forEach((item, index) => {
    (index === 0) ?
    heroCarouselIndicators.innerHTML += "<li data-bs-target='#heroCarousel' data-bs-slide-to='" + index + "' class='active'></li>":
      heroCarouselIndicators.innerHTML += "<li data-bs-target='#heroCarousel' data-bs-slide-to='" + index + "'></li>"
  });

  /**
   * Brands Slider
   */
  new Swiper('.brands-slider', {
    speed: 400,
    loop: true,
    // autoplay: {
    //   delay: 5000,
    //   disableOnInteraction: false
    // },
    slidesPerView: 'auto',
    pagination: {
      el: '.swiper-pagination',
      type: 'bullets',
      clickable: true
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
      clickable: true
    },
    breakpoints: {
      280: {
        slidesPerView: 2,
        spaceBetween: 0
      },
      520 : {
        slidesPerView: 3,
        spaceBetween: 0
      },
      760 : {
        slidesPerView: 4,
        spaceBetween: 0
      },
      992 : {
        slidesPerView: 5,
        spaceBetween: 0
      },
      1300: {
        slidesPerView: 6,
        spaceBetween: 0
      }
    }
  });

  /**
   * Section content
   */
  new Swiper('.section__content', {
    speed: 400,
    loop: true,
    slidesPerView: 'auto',
    pagination: {
      el: '.swiper-pagination',
      type: 'bullets',
      clickable: true
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
      clickable: true
    },
    breakpoints: {
      0: {
        slidesPerView: 1,
        spaceBetween: 0
      },
      576 : {
        slidesPerView: 2,
        spaceBetween: 24
      },
      992 : {
        slidesPerView: 3,
        spaceBetween: 24
      }
    }
  });

  

  /**
   * image slider
   */
  new Swiper('.imgs-slider', {
    speed: 400,
    loop: true,
    autoplay: {
      delay: 3800,
      disableOnInteraction: false
    },
    slidesPerView: 'auto',
    pagination: {
      el: '.swiper-pagination',
      type: 'bullets',
      clickable: true
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
      clickable: true
    },
    breakpoints: {
      0: {
        slidesPerView: 3,
        spaceBetween: 24
      }
    }
  });


  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  /**
   * Initiate tooltips
   */
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  })

  /**
   * Initiate quill editors
   */
  if (select('.quill-editor-default')) {
    new Quill('.quill-editor-default', {
      theme: 'snow'
    });
  }

  if (select('.quill-editor-bubble')) {
    new Quill('.quill-editor-bubble', {
      theme: 'bubble'
    });
  }

  if (select('.quill-editor-full')) {
    new Quill(".quill-editor-full", {
      modules: {
        toolbar: [
          [{
            font: []
          }, {
            size: []
          }],
          ["bold", "italic", "underline", "strike"],
          [{
              color: []
            },
            {
              background: []
            }
          ],
          [{
              script: "super"
            },
            {
              script: "sub"
            }
          ],
          [{
              list: "ordered"
            },
            {
              list: "bullet"
            },
            {
              indent: "-1"
            },
            {
              indent: "+1"
            }
          ],
          ["direction", {
            align: []
          }],
          ["link", "image", "video"],
          ["clean"]
        ]
      },
      theme: "snow"
    });
  }

  /**
   * Initiate TinyMCE Editor
   */
  const useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
  const isSmallScreen = window.matchMedia('(max-width: 1023.5px)').matches;

  tinymce.init({
    selector: 'textarea.tinymce-editor',
    plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons',
    editimage_cors_hosts: ['picsum.photos'],
    menubar: 'file edit view insert format tools table help',
    toolbar: 'undo redo | bold italic underline strikethrough | fontfamily fontsize blocks | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
    toolbar_sticky: true,
    toolbar_sticky_offset: isSmallScreen ? 102 : 108,
    autosave_ask_before_unload: true,
    autosave_interval: '30s',
    autosave_prefix: '{path}{query}-{id}-',
    autosave_restore_when_empty: false,
    autosave_retention: '2m',
    image_advtab: true,
    link_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_class_list: [{
        title: 'None',
        value: ''
      },
      {
        title: 'Some class',
        value: 'class-name'
      }
    ],
    importcss_append: true,
    file_picker_callback: (callback, value, meta) => {
      /* Provide file and text for the link dialog */
      if (meta.filetype === 'file') {
        callback('https://www.google.com/logos/google.jpg', {
          text: 'My text'
        });
      }

      /* Provide image and alt text for the image dialog */
      if (meta.filetype === 'image') {
        callback('https://www.google.com/logos/google.jpg', {
          alt: 'My alt text'
        });
      }

      /* Provide alternative source and posted for the media dialog */
      if (meta.filetype === 'media') {
        callback('movie.mp4', {
          source2: 'alt.ogg',
          poster: 'https://www.google.com/logos/google.jpg'
        });
      }
    },
    templates: [{
        title: 'New Table',
        description: 'creates a new table',
        content: '<div class="mceTmpl"><table width="98%%"  border="0" cellspacing="0" cellpadding="0"><tr><th scope="col"> </th><th scope="col"> </th></tr><tr><td> </td><td> </td></tr></table></div>'
      },
      {
        title: 'Starting my story',
        description: 'A cure for writers block',
        content: 'Once upon a time...'
      },
      {
        title: 'New list with dates',
        description: 'New List with dates',
        content: '<div class="mceTmpl"><span class="cdate">cdate</span><br><span class="mdate">mdate</span><h2>My List</h2><ul><li></li><li></li></ul></div>'
      }
    ],
    template_cdate_format: '[Date Created (CDATE): %m/%d/%Y : %H:%M:%S]',
    template_mdate_format: '[Date Modified (MDATE): %m/%d/%Y : %H:%M:%S]',
    height: 600,
    image_caption: true,
    quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    noneditable_class: 'mceNonEditable',
    toolbar_mode: 'sliding',
    contextmenu: 'link image table',
    skin: useDarkMode ? 'oxide-dark' : 'oxide',
    content_css: useDarkMode ? 'dark' : 'default',
    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
  });

  /**
   * Initiate Bootstrap validation check
   */
  var needsValidation = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(needsValidation)
    .forEach(function(form) {
      form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

  /**
   * Initiate Datatables
   */
  const datatables = select('.datatable', true)
  datatables.forEach(datatable => {
    new simpleDatatables.DataTable(datatable);
  })

  /**
   * Autoresize echart charts
   */
  const mainContainer = select('#main');
  if (mainContainer) {
    setTimeout(() => {
      new ResizeObserver(function() {
        select('.echart', true).forEach(getEchart => {
          echarts.getInstanceByDom(getEchart).resize();
        })
      }).observe(mainContainer);
    }, 200);
  }

  

})();

function CloseModal(event) {
  event.target.parentElement.parentElement.parentElement
  .classList.remove('auth-modal--open')
}

function changeModal(event) {
  let login = document.querySelector('.modal__contanier.modal__logIn');
  let register = login.nextElementSibling;

  let thisE = event.target.parentElement.parentElement.parentElement;
  
  if(thisE.contains(login)) {
    login.classList.add('hide');
    register.classList.remove('hide');
  } else {
    register.classList.add('hide');
    login.classList.remove('hide');
  }
}


function updateButtonState() {
  const inputs = document.querySelectorAll('.input-form__number--text');
  for (let input of inputs) {
          const value = input.value;
          const parent = input.parentElement;

          if (parent.children[0] && input.hasAttribute("min"))
              parent.children[0].disabled = value <= parseFloat(input.min);

          if (parent.children[2] && input.hasAttribute("max"))
              parent.children[2].disabled = value >= parseFloat(input.max);
  }
}

function handleNumberInputBlur(event) {
  const value = event.target.value;

  if (event.target.hasAttribute("min") && value < parseFloat(event.target.min))
      event.target.value = event.target.min;

  if (event.target.hasAttribute("max") && value > parseFloat(event.target.max))
      event.target.value = event.target.max;
    
  updateButtonState();
}

window.onload = updateButtonState;

function decreNumber(event) {
  let button = event.target;
  let input = button.nextElementSibling;

  if (input) {
      let value = parseFloat(input.value);
      value -= 1;

      if (input.hasAttribute("min") && value < parseFloat(input.min)) {
        value = input.min;
      }

      if (input.value !== value) {
        input.value = value;
        updateButtonState();
      }   
  }
}

function increNumber(event) {
  let button = event.target;
  let input = button.previousElementSibling;

  if (input) {
    let value = parseFloat(input.value);
    value += 1;

    if (input.hasAttribute("max") && value > parseFloat(input.max)) {
      value = input.max;
      button.disabled = true;
    }

    if (input.value !== value) {
      input.value = value;
      updateButtonState();
    }
  }
}


let productNeedArrs = document.querySelectorAll('.cart__product-need')
  

function updateProductNeed() {
  productNeedArrs.forEach(item => {
    let value = "";
    Array.from(item.querySelectorAll('.product__list'))
      .forEach(i => {
          value += i.dataset.name + ": " + i.dataset.value + ", "
      })
    item.querySelector('.cart__product-need-value')
      .innerText = value.slice(0, value.length-2);
  })
}

window.addEventListener('load', updateProductNeed)


let target = null;
let addEvent = false;
function handleProductNeed(event) {
  icon = event.target;
  if(
    document.querySelector('.cart__product-choose.open')
    && !document.querySelector('.cart__product-choose.open')
    .contains(event.target.nextElementSibling)
  )
    return;
  target = event.target.nextElementSibling;
  target.classList.toggle('open')
  icon.classList.toggle('icon_animation')
  event.stopPropagation();
  if(target && !addEvent) {
    addEvent = true;
    document.addEventListener('click', function handleClickOutside(event) {
      if (!target.contains(event.target) || target.contains(icon))  {
        target.classList.remove('open');
        icon.classList.remove('icon_animation')
      }
    });
    
  }
}

function submitProductNeed(event) {
  updateProductNeed();
  event.target.parentElement.classList.remove('open');
  event.target.parentElement.previousElementSibling.classList.remove('icon_animation');
}

function handlePreviewAvatar(event) {
  const link = URL.createObjectURL(event.target.files[0]);
  event.target.parentElement.parentElement.previousElementSibling.src = link;
}

function handleDeleteAvatar(event) {
  event.target.parentElement.previousElementSibling.src = "assets/img/other/avatar_default.jpg";
}



var citis = document.getElementById("city");
var districts = document.getElementById("district");
var wards = document.getElementById("ward");

if(citis) {
  var Parameter = {
    url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json", 
    method: "GET", 
    responseType: "application/json", 
  };
  var promise = axios(Parameter);
  promise.then(function (result) {
    renderCity(result.data);
  });
  
  function renderCity(data) {
    for (const x of data) {
    var opt = document.createElement('option');
     opt.value = x.Name;
     opt.text = x.Name;
     opt.setAttribute('data-id', x.Id);
     citis.options.add(opt);
    }
    citis.onchange = function () {
      district.length = 1;
      ward.length = 1;
      if(this.options[this.selectedIndex].dataset.id != ""){
        const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);
  
        for (const k of result[0].Districts) {
      var opt = document.createElement('option');
       opt.value = k.Name;
       opt.text = k.Name;
       opt.setAttribute('data-id', k.Id);
       district.options.add(opt);
        }
      }
    };
    district.onchange = function () {
      ward.length = 1;
      const dataCity = data.filter((n) => n.Id === citis.options[citis.selectedIndex].dataset.id);
      if (this.options[this.selectedIndex].dataset.id != "") {
        const dataWards = dataCity[0].Districts.filter(n => n.Id === this.options[this.selectedIndex].dataset.id)[0].Wards;
  
        for (const w of dataWards) {
      var opt = document.createElement('option');
       opt.value = w.Name;
       opt.text = w.Name;
       opt.setAttribute('data-id', w.Id);
       wards.options.add(opt);
        }
      }
    };
  }
}


// window.onclick = function(e) {
//   console.log(e.target);
// }

function addAddress(event) {
  let parent = event.target.parentElement.parentElement;
  parent.nextElementSibling.classList.add('show');
  parent.classList.add('hide');
}

function handleAddAddress(event) {
  let form = event.target.parentElement.parentElement.parentElement;
  let data = {};
  data.fullName = form['txtFullName'];
  data.phone = form['txtPhone'];
  data.city = form['city'];
  data.district = form['district'];
  data.ward = form['ward'];
  data.map = form['txtMap'];
  let isPass = true;
  // Kiểm tra họ tên
  const regexFullName = /^([\dAÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*)$/
  const regexPhone = /^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/;
  if(data.fullName.value == '') {
    data.fullName.nextElementSibling.textContent = 'Họ tên không được để trống'
    isPass = false;
  } else if(!regexFullName.test(data.fullName.value)) {
    data.fullName.nextElementSibling.textContent = 'Họ tên tiếng việt, viết hoa chữ cái đầu'
    isPass = false;
  } else {
    data.fullName.nextElementSibling.textContent = ''
  }

  // Kiểm tra số điện thoại
  if(data.phone.value == '') {
    data.phone.nextElementSibling.textContent = 'Số điện thoại không được để trống'
    isPass = false;
  } else if(!regexPhone.test(data.phone.value)) {
    data.phone.nextElementSibling.textContent = 'Số điện thoại không đúng định dạng'
    isPass = false;
  } else {
    data.phone.nextElementSibling.textContent = ''
  }

  if(data.city.value == "0") {
    data.city.nextElementSibling.textContent= 'Bạn chưa chọn Tỉnh/Thành phố'
    isPass = false;
  } else {
    data.city.nextElementSibling.textContent= ''
  }

  if(data.district.value == "0") {
    data.district.nextElementSibling.textContent= 'Bạn chưa chọn Quận/Huyện'
    isPass = false;
  } else {
    data.district.nextElementSibling.textContent= ''
  }

  if(data.ward.value == "0") {
    data.ward.nextElementSibling.textContent= 'Bạn chưa chọn Phường/Xã'
    isPass = false;
  } else {
    data.ward.nextElementSibling.textContent= ''
  }

  // Kiểm tra địa chỉ cụ thể
  if(data.map.value == '') {
    data.map.nextElementSibling.textContent = 'Địa chỉ cụ thể không được để trống'
    isPass = false;
  } else {
    data.map.nextElementSibling.textContent = ''
  }

  if(isPass) {
    form.classList.remove('show')
    form.previousElementSibling.classList.remove('hide')
    data.address = `${data.map.value}, ${data.ward.value}, ${data.district.value}, ${data.city.value}.`
    let ulTag = document.querySelector('.profile-address__list');
    ulTag.innerHTML  = ulTag.innerHTML += `
      <li>
        <div class="profile-address__left">
          <div class="profile-address__head">
            <p class="profile-address__name">${data.fullName.value}</p>
            <p class="profile-address__phone">${data.phone.value}</p>
          </div>
          <p class="profile-address__map">${data.address}</p>
        </div>
        <div class="profile-address__right">
          <p class="btn__update">Chỉnh sửa</p>
          <p class="btn__delete">Xóa</p>
          <p class="btn__set-default">Đặt làm mặc định</p>
        </div>
      </li>`
  }
}



