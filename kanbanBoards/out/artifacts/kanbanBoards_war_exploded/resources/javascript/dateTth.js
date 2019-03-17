$(document).ready(function() {
  $(".min_go_date").datepicker({changeYear: true, changeMonth: true, currentText: "Now", dateFormat:"Rmmdd", showButtonPanel: true});
  $(".datepicker").datepicker({changeYear: true, changeMonth: true, currentText: "Now", dateFormat:"yy-mm-dd", showButtonPanel: true});
});
$(function() {
  var old_generateMonthYearHeader = $.datepicker._generateMonthYearHeader;
  var old_formatDate = $.datepicker.formatDate;
  var old_parseDate = $.datepicker.parseDate;

  $.extend($.datepicker, {
    //��ܤ�����᪺value
    formatDate: function (format, date, settings) {
      var oformatDate = old_formatDate(format, date, settings);
      if(format == 'Rmmdd'){
        var d = date.getDate();
        var m = date.getMonth()+1;
        var y = date.getFullYear();
        var fm = function(v){
          return (v<10 ? '0' : '')+v;
        };

        if((y - 1911) >= 100) {y = y - 1911 ;}else { y = "0" + String(y - 1911);}
        return (y) +''+ fm(m) +''+ fm(d);
      }
      return oformatDate;

    },
    //�I���w�s�b�����parse
    parseDate: function (format, value, settings) {
      var v = new String(value);
      var Y,M,D;
      if(format == 'Rmmdd'){
        if(v.length==7){/*1001215*/
          Y = v.substring(0,3)-0+1911;
          M = v.substring(3,5)-0-1;
          D = v.substring(5,7)-0;
          return (new Date(Y,M,D));
        }else if(v.length==6){/*0981215*/

          Y = "0" + String(v.substring(0,2)-0+1911);
          M = v.substring(2,4)-0-1;
          D = v.substring(4,6)-0;
          return (new Date(Y,M,D));
        }
        return (new Date());
      }else {
        var oparseDate = old_parseDate.apply(this, [format, value, settings]);
        return (oparseDate);
      }
    },

    _generateMonthYearHeader: function (inst, drawMonth, drawYear, minDate, maxDate,    secondary, monthNames, monthNamesShort) {
      var dateFormat = this._get(inst, "dateFormat");
      var htmlYearMonth = old_generateMonthYearHeader.apply(this, [inst, drawMonth, drawYear, minDate, maxDate, secondary, monthNames, monthNamesShort]);
      if(dateFormat == 'Rmmdd'){
        if ($(htmlYearMonth).find(".ui-datepicker-year").length > 0) {
          htmlYearMonth = $(htmlYearMonth).find(".ui-datepicker-year").find("option").each(function (i, e) {
            console.log(e.textContent);
            if (Number(e.value) - 1911 > 0) {$(e).text(Number(e.textContent) - 1911);}

          }).end().end().get(0).outerHTML;
        }
      }
      return htmlYearMonth;
    }
  });
});