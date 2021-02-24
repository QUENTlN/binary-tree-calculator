package td3;

public abstract class OperationBinaire implements ExpressionArithmetique {

	protected ExpressionArithmetique eaLeft;
	protected ExpressionArithmetique eaRight;

	// constructeur de la classe
	public OperationBinaire(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		this.eaLeft = eaLeft;
		this.eaRight = eaRight;
	}

	public ExpressionArithmetique getEaLeft() {
		return this.eaLeft;
	}

	public ExpressionArithmetique getEaRight() {
		return this.eaRight;

	}

	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws DivisionParZeroException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws DivisionParZeroException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) throws DivisionParZeroException {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) throws Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, VariableSymbolique droite) throws DivisionParZeroException, Exception {
		return this;
	}


	protected ExpressionArithmetique simplifie(VariableSymbolique gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstSymbolique gauche, ConstEntiere droite) {
		return this;
	}
	protected ExpressionArithmetique simplifie(Multiplication gauche, Addition droite) throws Exception{
		return this;
	}
	
	protected ExpressionArithmetique simplifie(Multiplication gauche, Multiplication droite) {
		return this;
	}
	

	@Override
	public ExpressionArithmetique simplifier() throws Exception {

		ExpressionArithmetique res;
		this.eaLeft = this.eaLeft.simplifier();
		this.eaRight = this.eaRight.simplifier();

		// test élément neutre
		if (this instanceof OperationAvecElementNeutre) {
			if ((this.getEaRight().simplifier().toString()
					.equals(((OperationAvecElementNeutre) this).getElementNeutre()))) {
				return this.eaLeft;
			} else if (this instanceof OperationCommutative) {
				if ((this.getEaLeft().simplifier().toString()
						.equals(((OperationAvecElementNeutre) this).getElementNeutre()))) {
					return this.eaRight;
				}
			}
		}

		if (this instanceof Factorisable) {

			IdentiteRemarquableMapping mapIdentiteRemarquable = new IdentiteRemarquableMapping();
			// test de l'appartenance de l'expression dans la map identité remarquable
			if (mapIdentiteRemarquable.getIdentiteRemarquable(this) != null) {
				// retourne l'expression sous sa forme factorisée
				return mapIdentiteRemarquable.getIdentiteRemarquable(this);
			}


			// ((3*(a^2))+((6*(a*b))+(3*(b^2))))
			if (this.eaLeft instanceof Multiplication && this.eaRight instanceof Addition) {

				//si on a bien les deux multplications du membre de droite
				if(((OperationBinaire) this.eaRight).getEaLeft() instanceof Multiplication&&
						((OperationBinaire) this.eaRight).getEaRight() instanceof Multiplication) {

					// si premier 3 est une constEntiere
					if (((OperationBinaire) (this.eaLeft)).getEaLeft() instanceof ConstEntiere) {

						// si 6 du milieu est une constEntiere
						if (((OperationBinaire) ((OperationBinaire) this.eaRight).getEaLeft())
								.getEaLeft() instanceof ConstEntiere) {

							//si mult de a*b= double de mult1
							if((((ConstEntiere)((OperationBinaire) ((OperationBinaire) this.getEaRight()).getEaLeft()).getEaLeft()).getEntier()== 2*((ConstEntiere)((OperationBinaire) this.eaLeft).getEaLeft()).getEntier())) {
								
								// si premier 3= dernier 3
								if(((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight())
										.getEaLeft().equals(((OperationBinaire) this.eaLeft).getEaLeft())) {

									// si 1ere puissance est une puissance
									if ( ((OperationBinaire) this.eaLeft).getEaRight() instanceof Puissance) {

										// si 2eme puissance est une puissance
										if(((OperationBinaire) ((OperationBinaire) (this.eaRight)).getEaRight())
												.getEaRight() instanceof Puissance) {

											// si la 1ere puissance=2
											if(((ConstEntiere) ((OperationBinaire) ((OperationBinaire) (this.eaLeft))
													.getEaRight()).getEaRight()).getEntier()==2) {

												// si 2eme puissance=2
												if(((ConstEntiere) ((OperationBinaire) ((OperationBinaire) ((OperationBinaire)
														(this.eaRight)).getEaRight()).getEaRight()).getEaRight()).getEntier() ==2) {

													//si on a bien la multplication ab
													if(((OperationBinaire) ((OperationBinaire) this.eaRight).getEaLeft()).getEaRight() instanceof Multiplication) {


														//si a de (a^2) est une variableSymb
														if( ((OperationBinaire) ((OperationBinaire) this.eaLeft).getEaRight())
																.getEaLeft() instanceof VariableSymbolique) {

															//si b de (b^2) est une variable symbolique
															if(((OperationBinaire) ((OperationBinaire) ((OperationBinaire)
																	this.eaRight).getEaRight()).getEaRight()).getEaLeft()
																	instanceof VariableSymbolique) {

																//si le a de 6ab est égale à a du début
																if(((OperationBinaire) ((OperationBinaire) ((OperationBinaire) this.eaRight)
																		.getEaLeft()).getEaRight()).getEaLeft()==
																		((OperationBinaire) ((OperationBinaire) this.eaLeft).getEaRight()).getEaLeft()) {

																	//si le b de 6ab égale au b de la fin
																	if(((OperationBinaire) ((OperationBinaire) ((OperationBinaire) this.eaRight)
																			.getEaLeft()).getEaRight()).getEaRight()==
																			((OperationBinaire) ((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight())
																					.getEaRight()).getEaLeft()) {


																		return this.simplifie((Multiplication) this.eaLeft, (Addition) this.eaRight);
																	}
																}
															}
														}
													} 
												}
											}
										}
									} 
								}
							}
						}
					}
				}

			} else if (this instanceof Soustraction) {

				if (this.eaLeft instanceof Multiplication && this.eaRight instanceof Multiplication) {
					if(EgaliteEquation.egalite(this.eaLeft,this.eaRight)) {
						return new ConstEntiere(0);
					}else {

						// si 1ere puissance est puissance, égale à 2
						if (((OperationBinaire) this.eaLeft).getEaRight() instanceof Puissance
								&& ((ConstEntiere) ((OperationBinaire) ((OperationBinaire) this.eaLeft)
										.getEaRight()).getEaRight()).getEntier() == 2) { // pas besoin de
							// constEntier REMPLACER
							//si 2eme puissance est une puissance=2
							if (((OperationBinaire) this.eaRight).getEaRight() instanceof Puissance&&
									((ConstEntiere) ((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight())
											.getEaRight()).getEntier() == 2) {

								//si b est une variable symbolique 
								if (((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight())
										.getEaLeft() instanceof VariableSymbolique) {

									//si a est une variable symbolique
									if (((OperationBinaire) ((OperationBinaire) this.eaLeft).getEaRight())
											.getEaLeft() instanceof VariableSymbolique) {

										// si a!=b
										if(((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight())
												.getEaLeft()!=((OperationBinaire) ((OperationBinaire) this.eaLeft).getEaRight())
												.getEaLeft()) {

											//si les deux multiplicateurs sont bien 6
											if (EgaliteEquation.egalite(((OperationBinaire) this.getEaLeft()).getEaLeft(),
													((OperationBinaire) this.getEaRight()).getEaLeft())) {

												return this.simplifie((Multiplication) this.eaLeft,(Multiplication) this.eaRight);
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		}

		if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstEntiere) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;
			res = simplifie(gauche, droite);
		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstRationnelle) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstEntiere) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstRationnelle) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof VariableSymbolique && this.eaRight instanceof VariableSymbolique) {
			VariableSymbolique gauche = (VariableSymbolique) this.eaLeft;
			VariableSymbolique droite = (VariableSymbolique) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof VariableSymbolique && this.eaRight instanceof ConstEntiere) {
			VariableSymbolique gauche = (VariableSymbolique) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof VariableSymbolique && this.eaRight instanceof ConstRationnelle) {
			VariableSymbolique gauche = (VariableSymbolique) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof VariableSymbolique) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			VariableSymbolique droite = (VariableSymbolique) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof VariableSymbolique) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			VariableSymbolique droite = (VariableSymbolique) this.eaRight;
			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstSymbolique && this.eaRight instanceof ConstEntiere) {
			ConstSymbolique gauche = (ConstSymbolique) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;
			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof Multiplication) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			Multiplication droite = (Multiplication) this.eaRight;
			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof Multiplication) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			Multiplication droite = (Multiplication) this.eaRight;
			res = simplifie(gauche, droite);

		} else {
			res = this;
		}

		return res;

	}

	public ExpressionArithmetique simplifier(int i) throws Exception {
		ExpressionArithmetique droite = this.eaRight;
		ExpressionArithmetique gauche = this.eaLeft;

		if (eaLeft instanceof OperationBinaire) {
			gauche = ((OperationBinaire) eaLeft).simplifier(i);
		} else if (eaLeft instanceof VariableSymboliqueIndexee) {
			gauche = ((VariableSymboliqueIndexee) eaLeft).simplifier(i);
		} else if (eaLeft instanceof VariableSymbolique) {
			gauche = ((VariableSymbolique) eaLeft).simplifier(i);
		} else if (eaLeft instanceof OperationUnaire) {
			gauche = ((OperationUnaire) eaLeft).simplifier(i);
		}
		if (eaRight instanceof OperationBinaire) {
			droite = ((OperationBinaire) eaRight).simplifier(i);
		} else if (eaRight instanceof VariableSymboliqueIndexee) {
			droite = ((VariableSymboliqueIndexee) eaRight).simplifier(i);
		} else if (eaRight instanceof VariableSymbolique) {
			droite = ((VariableSymbolique) eaRight).simplifier(i);
		} else if (eaRight instanceof OperationUnaire) {
			gauche = ((OperationUnaire) eaRight).simplifier(i);
		}

		if (this instanceof Multiplication) {
			return new Multiplication(gauche, droite);
		} else if (this instanceof Puissance) {
			return new Puissance(gauche, droite);
		} else if (this instanceof Addition) {
			return new Addition(gauche, droite);
		} else if (this instanceof Soustraction) {
			return new Soustraction(gauche, droite);
		} else if (this instanceof Division) {
			return new Division(gauche, droite);
		}

		return this; // une exception ici

	}
}
