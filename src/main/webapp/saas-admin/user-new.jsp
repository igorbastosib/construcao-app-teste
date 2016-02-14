<jsp:include page="/saas-admin/assets/includes/header.html"></jsp:include>
<jsp:include page="/saas-admin/assets/includes/navbar.html"></jsp:include>

<!-- Add New User Start -->
<section id="user-new" class="pfblock-admin pfblock-gray">

	<div class="container-fluid main-container-admin">
		<div class="col-md-2 sidebar">
			<jsp:include page="/saas-admin/assets/includes/sidebar.html"></jsp:include>
		</div>
		<div class="col-md-6 content">
			<div class="row">
				<p class="title-pages-admin">Adicionar Novo Usuário</p>
			</div>
			<form onsubmit="return checkForm(this);" action="/saas/UserServlet.saas?comando=salvar">
				<div class="container-fluid main-container-admin">
					<div class="formulario_add_new_user">
						<div>
							<div class="your-name-user form-margin">
								<label>Nome de Usuário<span class="text-danger"> (Obrigatório)</span></label><input type="text"
									name="login" size="40" class="input-admin">
							</div>
							<div class="your-password form-margin">
								<label>Senha <span class="text-danger"> (Obrigatório)</span></label><input type="password"
									name="senha" size="40" class="input-admin">
							</div>
							<div class="your-password-again form-margin">
								<label>Repetir Senha <span class="text-danger"> (Obrigatório)</span></label><input type="password"
									name="rptsenha" size="40" class="input-admin">
							</div>
							<div>
								<input type="submit" name="comando"
									value="salvar" class="btn btn-lg-light btn-block right btn-success btn-admin medium ">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-4 content"></div>
	</div>

</section>
<jsp:include page="/saas-admin/assets/includes/footer.html"></jsp:include>

<!-- Scripts -->
<script src="/saas/assets/js/jquery-1.11.1.min.js"></script>
<script	src="/saas/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="/saas/assets/js/imagesloaded.pkgd.js"></script>
<script src="/saas/assets/js/jquery.sticky.js"></script>
<script src="/saas/assets/js/wow.min.js"></script>
<script src="/saas/assets/js/jquery.easypiechart.js"></script>
<script src="/saas/assets/js/waypoints.min.js"></script>
<script src="/saas/assets/js/jquery.cbpQTRotator.js"></script>
<script src="/saas/assets/js/custom.js"></script>
<script type="text/javascript" src="/saas/assets/js/validate.form.js" charset="utf-8"></script>

</body>
</html>
