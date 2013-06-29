(function () {
  "use strict";
  window.addEventListener('load', function windowLoadListener () {
    window.removeEventListener('load', windowLoadListener);
    var form = document.forms.contact;
    form.addEventListener('submit', function (e) {
      var link = ["mailto:tomg@fastmail.uk?subject=", encodeURIComponent(form.subject.value), "&body=", encodeURIComponent(form.body.value)].join('');
      window.location.href = link;
      e.preventDefault();
    }, false);
  });
}());